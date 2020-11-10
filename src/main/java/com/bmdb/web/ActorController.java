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

import com.bmdb.business.Actor;
import com.bmdb.db.ActorRepo;


@CrossOrigin           // Security related
@RestController      // I am a Controller!
@RequestMapping("/actors")
public class ActorController {
	/*A controller will implement 5 CRUD methods (Test in this order):
	 * 1) GET ALL
	 * 2) GET BY ID
	 * 3) POST - Insert
	 * 4) PUT - Update
	 * 5) DELETE - delete
*/
	@Autowired       // Wires database to your controller
	private ActorRepo actorRepo;
	
	//GET ALL Actors
	@GetMapping("/")
	public List <Actor> getAll() {
		return actorRepo.findAll();
		
	}
	//GET Actor BY ID
	@GetMapping("/{id}")
	public Optional <Actor> getbyId(@PathVariable int id) {
		return actorRepo.findById(id);
	}	
	
//ADD an Actor
	@PostMapping("/")
public Actor addMovie(@RequestBody Actor a) {
	a = actorRepo.save(a);
	return a;
	}

	// UPDATE an Actor
	@PutMapping("/")
	public Actor updateActor(@RequestBody Actor a) {
		a = actorRepo.save(a);
		return a;
	}
	
	//DELETE a Movie
	@DeleteMapping("/{id}")
	public Actor deleteMovie(@PathVariable int id) {
		// Optional type will wrap a movie
		Optional<Actor> a = actorRepo.findById(id);
		// isPresent will return true if a movie was found
		if (a.isPresent()) {
		actorRepo.deleteById(id);
		} else {
			System.out.println("Error - actor not found for id " + id);
		}
		return a.get();
	}
}

