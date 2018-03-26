package com.userapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.userapp.model.User;

@RestController
public class ClientUserController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/users", produces="application/json")
	public List<User> findAll(){
		String uri="http://USER-SERVICE/all-users";
		List<User> userList = restTemplate.getForObject(uri,List.class);
		return userList;
	}
	
	@RequestMapping(value="/users-email/{email}", produces="application/json")
	public List<User> findByEmail(@PathVariable("email") String email){
		String uri="http://user-SERVICE/users-by-email/"+email;
		List<User> userList = restTemplate.getForObject(uri,List.class);
		return userList;
	}
	@RequestMapping(value="/users/{userid}", produces="application/json")
	public User findById(@PathVariable("userid") int id){
		String uri="http://user-SERVICE/one-user/"+id;
		User user = restTemplate.getForObject(uri,User.class);
		return user;
	}
	@RequestMapping(value="/say-hello/{username}")
	public String greet(@PathVariable("username")String name){
		String uri="http://user-SERVICE/greet/"+name;
		String msg = restTemplate.getForObject(uri,String.class);
		return msg;
	}
}
