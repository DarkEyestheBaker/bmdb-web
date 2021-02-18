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


	import com.bmdb.business.*;
	import com.bmdb.db.*;

	@CrossOrigin           // Security related
	@RestController      // I am a Controller!
	@RequestMapping("/users")
	public class UserController {
		/*A controller will implement 5 CRUD methods (Test in this order):
		 * 1) GET ALL
		 * 2) GET BY ID
		 * 3) POST - Insert
		 * 4) PUT - Update
		 * 5) DELETE - delete
	*/
		@Autowired       // Wires database to your controller
		private UserRepo userRepo;
		//GET ALL Users
		@GetMapping("/")
		public List <User> getAll() {
			return userRepo.findAll();
			
		}
		//GET User BY ID
		@GetMapping("/{id}")
		public Optional <User> getbyId(@PathVariable int id) {
			return userRepo.findById(id);
		}	
		
	//ADD a User
		@PostMapping("/")
	public User addUser(@RequestBody User u) {
		u = userRepo.save(u);
		return u;
		}

		// UPDATE a User
		@PutMapping("/")
		public User updateUser(@RequestBody User u) {
			u = userRepo.save(u);
			return u;
		}
		
		//DELETE a User
		@DeleteMapping("/{id}")
		public User deleteUser(@PathVariable int id) {
			// Optional type will wrap a user
			Optional<User> u = userRepo.findById(id);
			// isPresent will return true if a user was found
			if (u.isPresent()) {
			userRepo.deleteById(id);
			} else {
				System.out.println("Error - user not found for id " + id);
			}
			return u.get();
		}
		//Login
		@PostMapping("/login")
		public Optional <User> login(@RequestBody User user) {
			return userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		}
	}



