package com.userLocation.service;

import com.userLocation.dtos.SignupDto;
import com.userLocation.exception.ProjectException;
import com.userLocation.model.MyRoles;
import com.userLocation.model.Role;
import com.userLocation.model.UserModel;
import com.userLocation.repository.RoleRepository;
import com.userLocation.repository.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The UserModelServiceImpl class implements the UserModelService and UserDetailsService interfaces
 * to provide user model management and user details retrieval functionalities.
 */
@Service
@RequiredArgsConstructor
public class UserModelServiceImpl implements UserModelService , UserDetailsService {


    private final RoleRepository roleRepository;

    private final UserModelRepository userModelRepository;

    /**
     * Registers a new user based on the provided signup information.
     *
     * @param signupDto the DTO containing the signup information
     * @return a message indicating the success of the signup process
     */
    @Override
    public String signup(SignupDto signupDto) {
             Role role = new Role();
            if(signupDto.getRole() == null || signupDto.getRole().equals("Reader") || signupDto.getRole().isEmpty()){
              role =  roleRepository.findByRole(MyRoles.READER);
            }
            else if(signupDto.getRole().equals("Admin")){
                role = roleRepository.findByRole(MyRoles.ADMIN);
            }

        UserModel userModel = new UserModel(signupDto.getName(), signupDto.getEmail(), signupDto.getPassword());

        List<Role> roles = new ArrayList<>();

        roles.add(role);
        userModel.setRole(roles);

        UserModel savedUserModel = userModelRepository.save(userModel);

        if(savedUserModel != null) {
            return "User Saved";
        }

        else{
            return "User not saved";
        }

    }

    /**
     * Retrieves the user details by username.
     *
     * @param username the username of the user
     * @return the UserDetails object containing the user details
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = userModelRepository.findByEmail(username).
                orElseThrow(() -> new ProjectException("User not found"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role: userModel.getRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
        }
        return new UserDetailsImpl(userModel.getEmail(), userModel.getPassword(), authorities);

    }
}
