package com.spring.security.OAuth;

import java.util.Optional;

public interface ApplicationUserDAO {
	
	Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
