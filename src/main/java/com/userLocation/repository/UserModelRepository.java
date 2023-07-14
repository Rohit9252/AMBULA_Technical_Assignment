package com.userLocation.repository;

import com.userLocation.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Integer> {

    public Optional<UserModel> findByEmail(String email );


}
