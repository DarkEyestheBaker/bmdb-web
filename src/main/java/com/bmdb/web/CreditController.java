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

import com.bmdb.business.Credit;
import com.bmdb.db.CreditRepo;

@CrossOrigin
@RestController
@RequestMapping("/credits")
public class CreditController {
	
	/*A controller will implement 5 CRUD methods (Test in this order):
	 * 1) GET ALL
	 * 2) GET BY ID
	 * 3) POST - Insert
	 * 4) PUT - Update
	 * 5) DELETE - delete
*/
	@Autowired       // Wires database to your controller
	private CreditRepo creditRepo;
	
	//GET ALL Credits
	@GetMapping("/")
	public List <Credit> getAll() {
		return creditRepo.findAll();
		
	}
	//GET Credit BY ID
	@GetMapping("/{id}")
	public Optional <Credit> getCredit(@PathVariable int id) {
		Optional<Credit> c = creditRepo.findById(id);
		if (c.isPresent()) {
	}
		return c;	}
	
	
//ADD a Credit
	@PostMapping("/")
public Credit addCredit(@RequestBody Credit c) {
	 creditRepo.save(c);
	return creditRepo.save(c);
	}

	// UPDATE a Credit
	@PutMapping("/")
	public Credit updateCredit(@RequestBody Credit c) {
		c = creditRepo.save(c);
		return c;
	}
	
	//DELETE a Credit
	@DeleteMapping("/{id}")
	public Credit deleteCredit(@PathVariable int id) {
		// Optional type will wrap a movie
		Optional<Credit> c = creditRepo.findById(id);
		// isPresent will return true if a movie was found
		if (c.isPresent()) {
		creditRepo.deleteById(id);
		} else {
			System.out.println("Error - credit not found for id " + id);
		}
		return c.get();
	}
}



