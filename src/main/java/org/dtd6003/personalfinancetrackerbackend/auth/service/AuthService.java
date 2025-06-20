package org.dtd6003.personalfinancetrackerbackend.auth.service;

import org.dtd6003.personalfinancetrackerbackend.auth.dto.RegisterRequest;
import org.dtd6003.personalfinancetrackerbackend.auth.model.User;
import org.dtd6003.personalfinancetrackerbackend.auth.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.dtd6003.personalfinancetrackerbackend.auth.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.dtd6003.personalfinancetrackerbackend.auth.dto.AuthResponse;

//remember business logic that talks to entity
@Service
public class AuthService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthService(UserRepository repo, PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public AuthResponse createUser(RegisterRequest req){
        //first check if email is unique if now throw exception
        if (repo.findByEmail(req.getEmail())) {
            System.out.println("Not unique email");
        }

        //create password hashing strategy and then add it to db by using setter
        String userPassword = req.getPassHash();
        String encodedPassHash = passwordEncoder.encode(userPassword);

        User newUser = new User(req.getEmail(),encodedPassHash);
        User saved = repo.save(newUser);

        //generate jwt for this new user
        String token = jwtTokenProvider.generateToken(saved.getId(), saved.getEmail());


        return new AuthResponse(token, saved.getId(), saved.getEmail()); //gives controller data
    }

}
