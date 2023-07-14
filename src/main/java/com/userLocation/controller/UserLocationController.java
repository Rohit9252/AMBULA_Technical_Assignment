package com.userLocation.controller;

import com.userLocation.model.UserLocation;
import com.userLocation.service.UserLocationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for user location-related operations such as creating, updating, and retrieving user locations.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserLocationController {

    private final UserLocationServiceImpl userLocationService;

    /**
     * Handles POST requests to the "/api/create_data" endpoint.
     * Creates a new user location.
     *
     * @param userLocation the {@link UserLocation} object representing the user location to be created
     * @return a {@link ResponseEntity} with the status and response body containing the created user location
     */
    @PostMapping("/create_data")
    @PreAuthorize("hasRole ('ADMIN')")
    public ResponseEntity<UserLocation> createDataHandler(@RequestBody UserLocation userLocation){

         return new ResponseEntity<>(userLocationService.saveUserLocation(userLocation), HttpStatus.CREATED);

    }

    /**
     * Handles POST requests to the "/api/create_data" endpoint.
     * Creates a new user location.
     *
     * @param userLocation the {@link UserLocation} object representing the user location to be created
     * @return a {@link ResponseEntity} with the status and response body containing the created user location
     */
    @PutMapping("/update_data/{id}")
    @PreAuthorize("hasRole ('ADMIN')")
    public ResponseEntity<UserLocation> updateDataHandler(@RequestBody UserLocation userLocation, @PathVariable Long id){

        System.out.println("id = " + id);
        return new ResponseEntity<>(userLocationService.updateUserLocation(userLocation, id), HttpStatus.OK);

    }

    /**
     * Handles GET requests to the "/api/get_users/{n}" endpoint.
     * Retrieves the nearest N user locations.
     *
     * @param n the number of nearest user locations to retrieve
     * @return a {@link ResponseEntity} with the status and response body containing the nearest user locations
     */
    @GetMapping("/get_users/{n}")
    @PreAuthorize("hasRole ('ADMIN', 'READER')" )
    public ResponseEntity<List<UserLocation>> getNUser(@PathVariable int n){

        return new ResponseEntity<>(userLocationService.getUsersLocations(n), HttpStatus.OK);

    }


    @GetMapping("/get_users")
    @PreAuthorize("hasRole ('ADMIN', 'READER')" )
    public ResponseEntity<List<UserLocation>> getAllNUser(){

        return new ResponseEntity<>(userLocationService.getAllUsersLocations(), HttpStatus.OK);

    }






}
