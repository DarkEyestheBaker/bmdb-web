package com.bmdb.web;

import java.util.List;
import java.util.Optional;

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

import com.bmdb.business.Movie;
import com.bmdb.db.MovieRepo;

@CrossOrigin           // Security related
@RestController      // I am a Controller!
@RequestMapping("/movies")
public class MovieController {
	/*A controller will implement 5 CRUD methods (Test in this order):
	 * 1) GET ALL
	 * 2) GET BY ID
	 * 3) POST - Insert
	 * 4) PUT - Update
	 * 5) DELETE - delete
*/
	@Autowired       // Wires database to your controller
	private MovieRepo movieRepo;
	
	//GET ALL Movies
	@GetMapping("/")
	public List <Movie> getAll() {
		return movieRepo.findAll();
		
	}
	//GET Movie BY ID
	@GetMapping("/{id}")
	public Optional <Movie> getbyId(@PathVariable int id) {
		return movieRepo.findById(id);
	}	
	
//ADD a Movie
	@PostMapping("/")
public Movie addMovie(@RequestBody Movie m) {
	m = movieRepo.save(m);
	return m;
	}

	// UPDATE a Movie
	@PutMapping("/")
	public Movie updateMovie(@RequestBody Movie m) {
		m = movieRepo.save(m);
		return m;
	}
	
	//DELETE a Movie
	@DeleteMapping("/{id}")
	public Movie deleteMovie(@PathVariable int id) {
		// Optional type will wrap a movie
		Optional<Movie> m = movieRepo.findById(id);
		// isPresent will return true if a movie was found
		if (m.isPresent()) {
		movieRepo.deleteById(id);
		} else {
			System.out.println("Error - movie not found for id " + id);
		}
		return m.get();
	}
}

