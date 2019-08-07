package com.poke.pokedex.model;

public class Stats {
	
	private int baseAttack;
	private int baseDefense;
	private int baseStamina;
	
	public Stats() {
	}
	
	public Stats(int baseAttack, int baseDefense, int baseStamina) {
		this.baseAttack = baseAttack;
		this.baseDefense = baseDefense;
		this.baseStamina = baseStamina;
	}
	
	public int getBaseAttack() {
		return baseAttack;
	}
	
	public void setBaseAttack(int baseAttack) {
		this.baseAttack = baseAttack;
	}
	
	public int getBaseDefense() {
		return baseDefense;
	}
	
	public void setBaseDefense(int baseDefense) {
		this.baseDefense = baseDefense;
	}
	
	public int getBaseStamina() {
		return baseStamina;
	}
	
	public void setBaseStamina(int baseStamina) {
		this.baseStamina = baseStamina;
	}
	
}
