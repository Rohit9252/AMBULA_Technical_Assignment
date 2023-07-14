package com.userLocation;

import com.userLocation.dtos.SignupDto;
import com.userLocation.exception.ProjectException;
import com.userLocation.model.MyRoles;
import com.userLocation.model.Role;
import com.userLocation.model.UserModel;
import com.userLocation.repository.RoleRepository;
import com.userLocation.repository.UserModelRepository;
import com.userLocation.service.UserModelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserLocationApplicationTests {

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private UserModelRepository userModelRepository;


	@InjectMocks
	private UserModelServiceImpl userModelService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		roleRepository.save(new Role(1, MyRoles.ADMIN));
		roleRepository.save(new Role( 2, MyRoles.READER));
	}

	@Test
	void contextLoads() {
	}




}
