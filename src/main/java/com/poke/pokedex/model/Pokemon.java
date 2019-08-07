package com.poke.pokedex.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class Pokemon {
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
	private int dex;
	
	private String name;
	private Stats stats;
	private Types[] types;

	public Pokemon() {
	}
	
	public Pokemon(int dex, String name, Stats stats, Types[] types) {
		this.dex = dex;
		this.name = name;
		this.stats = stats;
		this.types = types;
	}
	
	public int getDex() {
		return dex;
	}
	
	public void setDex(int dex) {
		this.dex = dex;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	public Types[] getTypes() {
		return types;
	}

	public void setTypes(Types[] types) {
		this.types = types;
	}
	
}
