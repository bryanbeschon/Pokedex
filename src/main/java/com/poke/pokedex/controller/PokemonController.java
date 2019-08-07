package com.poke.pokedex.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poke.pokedex.model.ModifPokemon;
import com.poke.pokedex.model.Pokemon;
import com.poke.pokedex.model.PokemonSuppr;
import com.poke.pokedex.model.Pokemonsuser;
 
@Controller
public class PokemonController
{
	public static final String personaldirectory = "/Users/bryanbeschon/";
	
    @RequestMapping(value = "/displayPokemons", method = RequestMethod.GET,
    		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Pokemon[] display() throws JsonParseException, JsonMappingException, IOException {

	Pokemon[] pokemons = null;
	ObjectMapper mapper = new ObjectMapper();

    //Read from file
    //Get file from resources folder
    //ClassLoader classLoader = getClass().getClassLoader();
    //File fi = new File(classLoader.getResource("pokemonsusers.json").getFile());
    File fi = new File(personaldirectory + "pokemonsusers.json");
    Pokemonsuser[] root = mapper.readValue(fi, Pokemonsuser[].class);
	
	if (!LoginController.userlogged.equals("")) {
		
		for (Pokemonsuser pokeuser : root) {
        	if (pokeuser.getUser().equals(LoginController.userlogged)){
        		pokemons=pokeuser.getPokemons();
        		break;
        	}
        }
		
	}
	
	else {
		System.out.println("Impossible, no user is logged. Please log in before.");
	}
	
	System.out.println("userlogged = "+LoginController.userlogged);
	
	return pokemons;
    }
    
    /*@RequestMapping(value = "/addPokemon", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String login(String newpokemon) throws JsonParseException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	
	//Read from file
	//Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	//File fi = new File(classLoader.getResource("pokemon.json").getFile());
	File fi = new File(personaldirectory + "idpasswords.json");
	LoginPassword[] root = mapper.readValue(fi1, LoginPassword[].class);
	
	boolean isfound = false;
	String answer = "";
	
	if (!LoginController.userlogged.equals("")) {
	
	    for (LoginPassword lpfile : root) {
	    	if (lp.getLogin().equals(lpfile.getLogin())){
	    		isfound=true;
	    		if(lp.getPassword().equals(lpfile.getPassword())) {
		        	answer="Login success for the user: " + lp.getLogin();
		        	LoginController.userlogged=lp.getLogin();
	        	}
	        	else {
	        		answer="Password error for the user: " + lp.getLogin();
	        	}	
	    		break;
	    	}
	    }
	    
		if (!isfound) {
			answer="Not existing user";
		}
	}
	
	else {
		answer="Impossible, no user is logged. Please log in before.";
	}
	
	System.out.println(LoginController.userlogged);
	return answer;
	}*/
    
    //modify the stats of a captured pokemon
    @RequestMapping(value = "/modifyPokemon", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String modifyPokemonStats(@RequestBody ModifPokemon mp) throws JsonParseException, JsonMappingException, IOException {
    	String answer = "";
    	boolean isfounduser = false;
    	boolean isfoundpoke = false;
    	ObjectMapper mapper = new ObjectMapper();
	
		//Read from file
		//Get file from resources folder
		//ClassLoader classLoader = getClass().getClassLoader();
		//File fi = new File(classLoader.getResource("pokemonsusers.json").getFile());
		File fi = new File(personaldirectory + "pokemonsusers.json");
		Pokemonsuser[] root = mapper.readValue(fi, Pokemonsuser[].class);
		
		if (!LoginController.userlogged.equals("")) {
			
			for (Pokemonsuser pokeuser : root) {
	        	if (pokeuser.getUser().equals(LoginController.userlogged)){
	        		isfounduser=true;
	        		for (Pokemon pok : pokeuser.getPokemons()) {
	        			if (pok.getName().equals(mp.getName())){
	        				isfoundpoke=true;
	        				//modifications occur if the new stat > 0
	        				if (mp.getStats().getBaseAttack()>0) {
	        					int oldatt = pok.getStats().getBaseAttack();
	        					int newatt = mp.getStats().getBaseAttack();
	        					pok.getStats().setBaseAttack(newatt);
	        					answer = "Attack of pokemon " + pok.getName() + " got modified "
	        							+ oldatt + " --> " + newatt + "\n";
	        				}
	        				if (mp.getStats().getBaseDefense()>0) {
	        					int olddef = pok.getStats().getBaseDefense();
	        					int newdef = mp.getStats().getBaseDefense();
	        					pok.getStats().setBaseDefense(newdef);
	        					answer = answer + "Defense of pokemon " + pok.getName() + " got modified "
	        							+ olddef + " --> " + newdef + "\n";
	        				}
	        				if (mp.getStats().getBaseStamina()>0) {
	        					int oldsta = pok.getStats().getBaseStamina();
	        					int newsta = mp.getStats().getBaseStamina();
	        					pok.getStats().setBaseStamina(newsta);
	        					answer = answer + "Stamina of pokemon " + pok.getName() + " got modified "
	        							+ oldsta + " --> " + newsta + "\n";
	        				}
	        					
	        					mapper.writeValue(new File(personaldirectory + "pokemonsusers.json"), root);
	        					System.out.println("Successfully updated!");
	        				
	        				break;
	        			}
	        		}
	    			if (!isfoundpoke) {
	    	    		answer="Problem. This user has not the pokemon " + mp.getName();
	    	    	}
	        		break;
	        	}
	        }
			
			if (!isfounduser) {
	    		answer="Problem. This user has 0 pokemon";
	    	}
			
		}
		
		else {
			answer = "Impossible, no user is logged. Please log in before.";
		}
		
		return answer;
	}
  //modify the stats of a captured pokemon
    @RequestMapping(value = "/deletePokemon", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deletePokemon(@RequestBody PokemonSuppr pokesup) throws JsonParseException, JsonMappingException, IOException {
    	String answer = "";
    	boolean isfounduser = false;
    	boolean isfoundpoke = false;
    	ObjectMapper mapper = new ObjectMapper();
	
		//Read from file
		//Get file from resources folder
		//ClassLoader classLoader = getClass().getClassLoader();
		//File fi = new File(classLoader.getResource("pokemonsusers.json").getFile());
		File fi = new File(personaldirectory + "pokemonsusers.json");
		Pokemonsuser[] root = mapper.readValue(fi, Pokemonsuser[].class);
		
		if (!LoginController.userlogged.equals("")) {
			
			for (Pokemonsuser pokeuser : root) {
	        	if (pokeuser.getUser().equals(LoginController.userlogged)){
	        		isfounduser=true;
	        		for (int k=0;k<pokeuser.getPokemons().length;k++) {
	        			Pokemon pokdel = pokeuser.getPokemons()[k];
	        			if (pokdel.getName().equals(pokesup.getName())){
	        				isfoundpoke=true;
	        				pokdel=null;
	        				
	        				int size = pokeuser.getPokemons().length;
	        				Pokemon newtab[]= new Pokemon[size-1];
	        				for (int g=0;g<k;g++) {
	        					newtab[g]=pokeuser.getPokemons()[g];
	        				}
	        				for (int g=k;g<size-1;g++) {
	        					newtab[g]=pokeuser.getPokemons()[g+1];
	        				}
	        				
	        				pokeuser.setPokemons(null);
	        				pokeuser.setPokemons(newtab);
	        				
	        				
	        		/*for (Pokemon pok : pokeuser.getPokemons()) {
	        			if (pok.getName().equals(pokesup.getName())){
	        				isfoundpoke=true;
	        					
	        					
	        					pok.setDex(0);
	        					pok.setName(null);
	        					
	        					for (int i=0;i<pok.getTypes().length;i++) {
	        						
		        					pok.getTypes()[i].setId(null);
		        					pok.getTypes()[i].setName(null);
		        					pok.getTypes()[i]=null;
	        					}
	        					
	        					pok.setTypes(null);
	        						
	        					pok.getStats().setBaseAttack(0);
	        					pok.getStats().setBaseDefense(0);
	        					pok.getStats().setBaseStamina(0);
	        					
	        					pok.setStats(null);
	        					
	        					pok=null;*/
	        					
	        					
	        					mapper.writeValue(new File(personaldirectory + "pokemonsusers.json"), root);
	        					answer="Pokemon " + pokesup.getName() + " has been deleted";
	        					System.out.println("Successfully deleted!");
	        				
	        				break;
	        			}
	        		}
	    			if (!isfoundpoke) {
	    	    		answer="Problem. This user has not the pokemon " + pokesup.getName();
	    	    	}
	        		break;
	        	}
	        }
			
			if (!isfounduser) {
	    		answer="Problem. This user has 0 pokemon";
	    	}
			
		}
		
		else {
			answer = "Impossible, no user is logged. Please log in before.";
		}
		
		return answer;
	}
}