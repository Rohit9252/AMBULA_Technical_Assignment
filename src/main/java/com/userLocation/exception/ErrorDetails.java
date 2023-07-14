package com.userLocation.exception;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime timeStamp;
    private String mssg;
    private String description;

    /**
     * Default constructor for the ErrorDetails class.
     * Initializes the timeStamp field with the current date and time.
     */
    public ErrorDetails(){
        this.timeStamp = LocalDateTime.now();

    }

    /**
     * Constructor for the ErrorDetails class.
     * Initializes the timeStamp, mssg, and description fields.
     *
     * @param mssg        the error message
     * @param description the error description
     */

    public ErrorDetails( String mssg , String description ){
        this();
        this.mssg = mssg;
        this.description = description;
    }

}
