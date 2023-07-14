package com.userLocation.service;

import com.userLocation.exception.UserLocationException;
import com.userLocation.model.UserLocation;
import com.userLocation.repository.UserLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
/**
 * The UserLocationServiceImpl class implements the UserLocationService interface and provides
 * methods for managing user locations.
 */
@Service
@RequiredArgsConstructor
public class UserLocationServiceImpl implements UserLocationService{

    private final UserLocationRepository userLocationRepository;

    /**
     * Saves a user location in the database.
     *
     * @param userLocation the user location to save
     * @return the saved user location
     */
    @Override
    public UserLocation saveUserLocation(UserLocation userLocation) {

        return userLocationRepository.save(userLocation);

    }


    /**
     * Updates a user location in the database.
     *
     * @param userLocation the updated user location
     * @param id           the ID of the user location to update
     * @return the updated user location
     * @throws UserLocationException if the user location is not found
     */
    @Override
    public UserLocation updateUserLocation(UserLocation userLocation, Long id) {


          UserLocation  userLocation1 =    userLocationRepository
                                            .findById(id).orElseThrow(()-> new UserLocationException("User Location not found"));

            userLocation1.setLatitude(userLocation.getLatitude());
            userLocation1.setLongitude(userLocation.getLongitude());
           userLocation1.setName(userLocation.getName());

              return userLocationRepository.save(userLocation1);

    }


    /**
     * Updates a user location in the database.
     *
     * @param userLocation the updated user location
     * @param id           the ID of the user location to update
     * @return the updated user location
     * @throws UserLocationException if the user location is not found
     */
    @Override
    public List<UserLocation> getUsersLocations(int n) {

        double latitude = 0;
        double longitude = 0;

        List<UserLocation> allUserLocations = userLocationRepository.findAll();

        allUserLocations.sort(Comparator.comparingDouble(location ->
                calculateDistance(latitude, longitude, location.getLatitude(), location.getLongitude())
        ));

        return allUserLocations.subList(0, Math.min(n, allUserLocations.size()));

    }


    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // Earth's radius in kilometers

        // Convert latitude and longitude values to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences between coordinates
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Apply the Haversine formula
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance
        return earthRadius * c;
    }
}
