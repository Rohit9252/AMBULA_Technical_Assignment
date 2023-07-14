package com.userLocation.controller;


import com.userLocation.dtos.JwtResponse;
import com.userLocation.dtos.LoginDto;
import com.userLocation.dtos.SignupDto;
import com.userLocation.model.UserModel;
import com.userLocation.service.LoginHandler;
import com.userLocation.service.UserModelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class for user-related operations such as signup, login, and retrieving current user information.
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {


    private final UserModelServiceImpl userModelServiceImpl;


    private final  PasswordEncoder passwordEncoder;



    private final LoginHandler loginHandler;


    /**
     * Handles POST requests to the "/api/signup" endpoint.
     *
     * @param signupDto the signup data transfer object containing user information
     * @return a {@link ResponseEntity} with the status and response body
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signupHandler(@RequestBody SignupDto signupDto){


        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));

         return new ResponseEntity<>(userModelServiceImpl.signup(signupDto), HttpStatus.CREATED);


    }


    /**
     * Handles POST requests to the "/api/login" endpoint.
     *
     * @param loginDto the login data transfer object containing user credentials
     * @return a {@link ResponseEntity} with the status and response body containing the JWT response
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginHandler(@RequestBody LoginDto loginDto) {


        return ResponseEntity.ok(loginHandler.login(loginDto));

    }


    /**
     * Handles GET requests to the "/api/getUser" endpoint.
     *
     * @return a {@link ResponseEntity} with the status and response body containing the current user information
     */
    @GetMapping("/getUser")
    public ResponseEntity<UserModel> getCurrentUserHandler(){

        return ResponseEntity.ok(loginHandler.getCurrentUser());

    }







}
