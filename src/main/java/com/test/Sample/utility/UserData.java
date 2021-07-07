package com.test.Sample.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;


public class UserData {

	public static List<User> getAllUsers(){
		List<User> users=new ArrayList();
		User user1=new User("rajat", "rajat123", new ArrayList<>());
		User user2=new User("roy", "roy123", new ArrayList<>());
		users.add(user1);
		users.add(user2);
		return users;
	}
}
