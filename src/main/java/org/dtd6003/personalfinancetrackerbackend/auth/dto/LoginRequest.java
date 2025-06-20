package org.dtd6003.personalfinancetrackerbackend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String passHash;

    public LoginRequest(){};

    public LoginRequest(String email, String passHash){
        this.email = email;
        this.passHash = passHash;
    }

    //set getters n setters
    public String getEmail(){
        return this.email;
    }

    public String getPassHash(){
        return this.passHash;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassHash(String passHash){
        this.passHash = passHash;
    }
}
