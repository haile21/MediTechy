package com.meditechy.exception;

public class ProfessionalNotFoundException extends RuntimeException{

    public ProfessionalNotFoundException(String message){
        super(message);
    }
}
