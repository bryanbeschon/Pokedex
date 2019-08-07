package com.poke.pokedex.model;

public class Types {
	
	private String id;
	private String name;
	
	public Types(){
	}
	
	public Types(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
