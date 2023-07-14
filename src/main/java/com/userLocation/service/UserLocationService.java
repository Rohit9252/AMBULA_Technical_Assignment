package com.userLocation.service;

import com.userLocation.model.UserLocation;

import java.util.List;

/**
 * The UserLocationService interface provides methods for managing user locations.
 */
public interface UserLocationService {

    /**
     * Saves a user location in the database.
     *
     * @param userLocation the user location to save
     * @return the saved user location
     */
    public UserLocation saveUserLocation(UserLocation userLocation);

    /**
     * Updates a user location in the database.
     *
     * @param userLocation the updated user location
     * @param id           the ID of the user location to update
     * @return the updated user location
     */
    public UserLocation updateUserLocation(UserLocation userLocation, Long id);

    /**
     * Retrieves a list of N nearest user locations from the database.
     *
     * @param n the number of nearest user locations to retrieve
     * @return a list of user locations
     */
    public List<UserLocation> getUsersLocations(int n );






}
