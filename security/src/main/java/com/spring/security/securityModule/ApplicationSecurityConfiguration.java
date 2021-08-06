package com.spring.security.securityModule;

import static com.spring.security.securityModule.ApplicationUserPermission.COURSE_WRITE;
import static com.spring.security.securityModule.ApplicationUserRole.ADMIN;
import static com.spring.security.securityModule.ApplicationUserRole.ADMINTRAINEE;
import static com.spring.security.securityModule.ApplicationUserRole.STUDENT;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.security.OAuth.ApplicationUserService;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	private final PasswordEncoder passwordEncoder;
	
	
	private final ApplicationUserService applicationUserService;
	
	
	@Autowired
	public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder,
			ApplicationUserService applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
		//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.csrf().disable()
	//	.and()
		.authorizeRequests()
		.antMatchers("/" ,"/index").permitAll()
		.antMatchers("/Api/**").hasRole(STUDENT.name())
		.antMatchers(HttpMethod.DELETE, "/Management/Api/**").hasAuthority(COURSE_WRITE.getPermissions())
		.antMatchers(HttpMethod.POST, "/Management/Api/**").hasAuthority(COURSE_WRITE.getPermissions())
		.antMatchers(HttpMethod.PUT, "/Management/Api/**").hasAuthority(COURSE_WRITE.getPermissions())
		.antMatchers(HttpMethod.GET, "/Management/Api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
	    .loginPage("/login").permitAll()
	    .defaultSuccessUrl("/courses", true)
		.and()
	    .rememberMe().tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21)).key("somethingverysecured")
        .and()
	    .logout()
	       .logoutUrl("/logout")
	       .clearAuthentication(true)
	       .invalidateHttpSession(true)
	       .deleteCookies("JSESSIONID","remember-me")
	       .logoutSuccessUrl("/login");
	    
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 provider.setPasswordEncoder(passwordEncoder);
		 provider.setUserDetailsService(applicationUserService);
		 
		 return provider;
	}	
	
}

