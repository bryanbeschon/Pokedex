package com.poke.pokedex.controller;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poke.pokedex.model.LoginPassword;


public class Test {

    public static void main(String[] args) throws JSONException, IOException 
    {
        ObjectMapper mapper = new ObjectMapper();
        //test to log on bryan's account
        String login = "bryan";
        String password = "pw";

        //Read from file
        LoginPassword[] root = mapper.readValue(new File("idpasswords.json"), LoginPassword[].class);

        //String val_newer = jo.getString(key);
        boolean isfound = false;
        for (LoginPassword lp : root) {
        	if (lp.getLogin().equals(login)){
        		isfound=true;
        		if(lp.getPassword().equals(password)) {
    	        	System.out.println("Login success for the user: " + login);
            	}
            	else {
            		System.out.println("Password error for the user: " + login);
            	}	
        		break;
        	}
        }
        
    	if (!isfound) {
    		System.out.println("Not existing user");
    	}

    }
}
