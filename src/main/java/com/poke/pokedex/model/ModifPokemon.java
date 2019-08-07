package com.poke.pokedex.model;

public class ModifPokemon {
	private String name;
	private Stats stats;
	
	public ModifPokemon() {
	}
	
	public ModifPokemon(String name, Stats stats) {
		this.name = name;
		this.stats = stats;
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
	
}
