package org.dtd6003.personalfinancetrackerbackend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=8)
    private String passHash;

    public RegisterRequest(){};

    public RegisterRequest(String email, String passHash){
        this.email = email;
        this.passHash = passHash;
    }

    //create getters for the DTO
    public String getEmail(){
        return this.email;
    }

    public String getPassHash(){
        return this.passHash;
    }

    //create setters for the DTO
    public void setEmail(String email){
        this.email = email;
    }

    public void setPassHash(String passHash){
        this.passHash = passHash;
    }

}
