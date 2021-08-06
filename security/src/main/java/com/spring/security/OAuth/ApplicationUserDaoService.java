package com.spring.security.OAuth;

import static com.spring.security.securityModule.ApplicationUserRole.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;


@Repository("fake")
public class ApplicationUserDaoService implements ApplicationUserDAO {
	
	@Autowired
	private PasswordEncoder PasswordEncoder;

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		// TODO Auto-generated method stub
		return getApplicationUsers().stream().filter(applicationuser-> username.equals(applicationuser.getUsername())).findFirst();
	}
	
	public List<ApplicationUser> getApplicationUsers()
	{
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(STUDENT.getGrantedAuthorities(), 
						PasswordEncoder.encode("password"), 
						"student",
						true, 
						true, 
						true, 
						true ),
				new ApplicationUser(ADMIN.getGrantedAuthorities(), 
						PasswordEncoder.encode("password"), 
						"admin", 
						true, 
						true, 
						true, 
						true ),
				new ApplicationUser(ADMINTRAINEE.getGrantedAuthorities(), 
						PasswordEncoder.encode("password"), 
						"admintrainee", 
						true, 
						true, 
						true, 
						true )
				);
		        return  applicationUsers;
	}

}
