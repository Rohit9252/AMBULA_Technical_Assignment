package com.userLocation.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    private MyRoles role;


    public Role(MyRoles reader) {
        this.role = reader;
    }
}
