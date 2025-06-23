package org.dtd6003.personalfinancetrackerbackend.auth.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when someone tries to register with an email that’s already taken.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String email) {
        super("Email already in use: " + email);
    }
}