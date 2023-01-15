package io.getarray.userservice;

import io.getarray.userservice.domain.Role;
import io.getarray.userservice.domain.User;
import io.getarray.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}


	@Bean
	 PasswordEncoder passwordEncoder(){
		return new  BCryptPasswordEncoder();

	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			// create role
			userService.saveRole(new Role( null, "ROLE_USER"));
			userService.saveRole(new Role( null, "ROLE_MANGER"));
			userService.saveRole(new Role( null, "ROLE_ADMIN"));
			userService.saveRole(new Role( null, "ROLE_SUPER_ADMIN"));

			// create user

			userService.saveUser(new User(null, "John Travolta", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Will Smith","will","1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Jim Carr","jim","1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Arnold Svolta", "arnold","1234",new ArrayList<>()));
			userService.addRoleToUsername("john","ROLE_USER" );
			userService.addRoleToUsername("john","ROLE_MANGER" );
			userService.addRoleToUsername("will","ROLE_MANGER" );
			userService.addRoleToUsername("john","ROLE_ADMIN" );
			userService.addRoleToUsername("arnold","ROLE_SUPER_ADMIN" );
			userService.addRoleToUsername("arnold","ROLE_ADMIN" );

		};
	}

}
