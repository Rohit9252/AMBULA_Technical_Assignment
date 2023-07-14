package com.userLocation.service;

import com.userLocation.dtos.SignupDto;

/**
 * The UserModelService interface defines methods for managing user models.
 */
public interface UserModelService {
    /**
     * Registers a new user based on the provided signup information.
     *
     * @param signupDto the DTO containing the signup information
     * @return a message indicating the success of the signup process
     */

    public String signup(SignupDto signupDto);


}
