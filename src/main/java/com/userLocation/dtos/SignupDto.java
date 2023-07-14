package com.userLocation.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data transfer object representing the signup information.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {


    private String name;
    private String password;
    private String email;

    private String role;

}
