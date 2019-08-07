package com.poke.pokedex.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class Pokemonsuser {
	
	private String user;
	private Pokemon[] pokemons;

	public Pokemonsuser() {
	}

	public Pokemonsuser(String user, Pokemon[] pokemons) {
		this.user = user;
		this.pokemons = pokemons;
	}

	public String getUser() {
		return user;
	}

	public void setLogin(String user) {
		this.user = user;
	}

	public Pokemon[] getPokemons() {
		return pokemons;
	}
	
	public void setPokemons(Pokemon[] pokemons) {
		this.pokemons = pokemons;
	}

	public void setPassword(Pokemon[] pokemons) {
		this.pokemons = pokemons;
	}

	@Override
	public String toString() {
		return "LoginPassword [user=" + user + ", Pokemons=" + pokemons.toString() + "]";
	}

}
