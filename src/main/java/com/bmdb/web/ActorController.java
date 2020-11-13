package com.bmdb.web;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	public List <Actor> getAllActors() {
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
	//LIST ALL Actors by Gender
	//Using RequestParam to pass Gender
		@GetMapping("/find-by-gender")
		public List <Actor> getAllActors(@RequestParam String gender) {
			return actorRepo.findByGender(gender);
		}			
		//LIST ALL Actors by lastName
		//Using RequestParam to pass lastName
			@GetMapping("/find-by-lastname")
			public List <Actor> getAll(@RequestParam String lastName) {
				return actorRepo.findByLastName(lastName);
}
			//LIST ALL Actors whose last name starts with "letter"
			//Using RequestParam to pass first letter of last name
				@GetMapping("/find-by-lastname-starts-with")
				public List <Actor> getActorsLastNameStarts(@RequestParam String letter) {
					return actorRepo.findByLastNameLike(letter + "%");
	}
				//LIST all actors born between d1 and d2
				@GetMapping("/find-by-birthdate-between")
				public List <Actor> getActorsByBirthDateBetween(@RequestParam String d1, @RequestParam String d2) {
					LocalDate ld1 = LocalDate.parse(d1);
					LocalDate ld2 = LocalDate.parse(d2);
				
					return actorRepo.findByBirthDateBetween(ld1, ld2);
				}
				
}

