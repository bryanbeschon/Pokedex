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
import com.poke.pokedex.model.LoginPassword;
 
@Controller
public class LoginController
{
	public static final String personaldirectory = "/Users/bryanbeschon/";
	//static variable used to know the login of the current user
	//is equal to "" if no user is logged
	public static String userlogged="";
	
    @RequestMapping(value = "/login", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            //produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String login(@RequestBody LoginPassword lp) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        //Read from file
        //Get file from resources folder
        //ClassLoader classLoader = getClass().getClassLoader();
        //File fi = new File(classLoader.getResource("idpasswords.json").getFile());
        File fi = new File(personaldirectory + "idpasswords.json");
        LoginPassword[] root = mapper.readValue(fi, LoginPassword[].class);

        boolean isfound = false;
        String answer = "";
        
        if (userlogged.equals("")) {

	        for (LoginPassword lpfile : root) {
	        	if (lp.getLogin().equals(lpfile.getLogin())){
	        		isfound=true;
	        		if(lp.getPassword().equals(lpfile.getPassword())) {
	    	        	answer="Login success for the user: " + lp.getLogin();
	    	        	userlogged=lp.getLogin();
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
        	answer="Impossible, the user " + userlogged + " is already logged. Please log out before.";
        }
        
        return answer;
  }
    
    @RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String logout() {

	String answer2 = "";
	
	if (!userlogged.equals("")) {
		
		answer2="The user "+userlogged+" is correctly logged out.";
		userlogged="";
		
	}
	
	else {
		answer2="Impossible, no user is logged.";
	}
	
	System.out.println("userlogged = "+userlogged);
	return answer2;
    }

}