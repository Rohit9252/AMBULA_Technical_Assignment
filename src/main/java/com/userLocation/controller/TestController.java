package com.userLocation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for testing access levels and authorization.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * Handles requests to the "/api/test/all" endpoint.
     *
     * @return a string representing public content
     */
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }


    /**
     * Handles requests to the "/api/test/user" endpoint.
     * Requires the user to have the "READER" role.
     *
     * @return a string representing user content
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('READER')")
    public String userAccess() {
        return "User Content.";
    }



    /**
     * Handles requests to the "/api/test/admin" endpoint.
     * Requires the user to have the "ADMIN" role.
     *
     * @return a string representing admin content
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole ('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}