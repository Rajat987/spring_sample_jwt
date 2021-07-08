package com.test.Sample.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.Sample.utility.UserData;

@Service
public class UserDetailsService  implements org.springframework.security.core.userdetails.UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users=UserData.getAllUsers();
		
		//User user=users.stream().filter((u)->u.getUsername().equals(username)).collect(Collectors.toList()).get(0);
		
		return new User("rajat","rajat",new ArrayList<>());
		
		
	}

}
