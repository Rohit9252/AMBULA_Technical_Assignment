package com.userLocation.repository;

import com.userLocation.model.MyRoles;
import com.userLocation.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByRole(MyRoles roles);

}
