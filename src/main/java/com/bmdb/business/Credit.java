package com.bmdb.business;

import javax.persistence.*;

@Entity
public class Credit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ActorId")
	private Actor actor;
	
	@ManyToOne
	@JoinColumn(name="MovieId")
	private Movie movie;
	private String role;
	
	public Credit() {
		super();
	}

	public Credit(int id, Actor actor, Movie movie, String role) {
		super();
		this.id = id;
		this.actor = actor;
		this.movie = movie;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	}
	