package com.spring.security.OAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService{

	
	private final ApplicationUserDAO ApplicationUserDAO;
	
	@Autowired
	public ApplicationUserService(@Qualifier("fake") ApplicationUserDAO applicationUserDAO) {
		ApplicationUserDAO = applicationUserDAO;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return ApplicationUserDAO.selectApplicationUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
	}

}
