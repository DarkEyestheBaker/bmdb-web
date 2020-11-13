package com.bmdb.db;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmdb.business.Actor;


public interface ActorRepo extends JpaRepository<Actor, Integer> {
	//Find actors by gender
	List<Actor> findByGender(String gender);

	List<Actor> findByLastName(String lastName);
	
	//"Find" = SELECT *   
	//"By" = WHERE  
	//jpa/Spring add "from actor"
	//Looks in lastName
	//"Like" can't find - so looks in java
	
	List<Actor> findByLastNameLike(String letter);
	
	//Find actors born in the 1960s

	List<Actor> findByBirthDateBetween(LocalDate ld1, LocalDate ld2);
}



