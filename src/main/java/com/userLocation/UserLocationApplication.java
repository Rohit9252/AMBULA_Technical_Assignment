package com.userLocation;

import com.userLocation.model.MyRoles;
import com.userLocation.model.Role;
import com.userLocation.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserLocationApplication {


	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserLocationApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return agrs ->{
			roleRepository.save(new Role(1, MyRoles.ADMIN));
			roleRepository.save(new Role( 2, MyRoles.READER));
		};
	}


}
