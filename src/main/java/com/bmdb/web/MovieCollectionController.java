package com.bmdb.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmdb.business.*;
import com.bmdb.db.*;


	@CrossOrigin           // Security related
	@RestController      // I am a Controller!
	@RequestMapping("/movie-collection")
	public class MovieCollectionController {
		/*A controller will implement 5 CRUD methods (Test in this order):
		 * 1) GET ALL
		 * 2) GET BY ID
		 * 3) POST - Insert
		 * 4) PUT - Update
		 * 5) DELETE - delete
		 */
		@Autowired       // Wires database to your controller
		private MovieCollectionRepo movieCollectionRepo;
		
		@Autowired  //Declaring an extra repo
		private UserRepo userRepo;
		
	    //Get all movieCollections
	    @GetMapping("/")
	    public List<MovieCollection> getAll() {
	        return movieCollectionRepo.findAll();
	    }
	    
	    //Get a movieCollection by id
	    @GetMapping("/{id}")
	    public Optional<MovieCollection> getById(@PathVariable int id) {
	        return movieCollectionRepo.findById(id);
	    }
	    
	    //Add a movieCollection
	    //Also recalculate the collection value in User
	    @PostMapping("/")
	    public MovieCollection addMovieCollection(@RequestBody MovieCollection m) {
	        m = movieCollectionRepo.save(m);
	        
	        recalculateCollectionValue(m);
	        
	        return m;
	    }
	    private void recalculateCollectionValue(MovieCollection m) {
	        //get all movie collections for this user
	        //loop through them and sum a new total
	        double newTotal = 0.0;
	        List <MovieCollection> mcs = movieCollectionRepo.findByUserId(m.getUser().getId());
	        for (MovieCollection mc : mcs) {
	            newTotal += mc.getPurchasePrice();
	        }
	        
	        User u = m.getUser();
	        u.setCollectionValue(newTotal);
	        userRepo.save(u);
	    }
	    
	    //Update a movieCollection
	    //Also recalculate the collection value in User
	    @PutMapping("/")
	    public MovieCollection updateMovieCollection(@RequestBody MovieCollection m) {
	        m = movieCollectionRepo.save(m);
	        recalculateCollectionValue(m);
	        return m;
	    }
	    
	    //Delete a movieCollection
	    //Also recalculate the collection value in User
	    @DeleteMapping("/{id}")
	    public MovieCollection deleteMovieCollection(@PathVariable int id) {
	        //Optional type will wrap a movieCollection
	        Optional<MovieCollection> m = movieCollectionRepo.findById(id);
	        //isPresent() will return true if a movieCollection was found
	        if (m.isPresent()) {
	            movieCollectionRepo.deleteById(id);
	            recalculateCollectionValue(m.get());
	        }
	        else {
	            System.out.println("Error - movie collection not found for id "+id);
	        }
	        return m.get();
	    }

	}



