package org.dtd6003.personalfinancetrackerbackend.auth.service;

import org.dtd6003.personalfinancetrackerbackend.auth.dto.LoginRequest;
import org.dtd6003.personalfinancetrackerbackend.auth.dto.RegisterRequest;
import org.dtd6003.personalfinancetrackerbackend.auth.model.User;
import org.dtd6003.personalfinancetrackerbackend.auth.repo.UserRepository;
import org.dtd6003.personalfinancetrackerbackend.auth.security.EmailAlreadyExistException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        //first check if email is unique if not throw exception
        if (repo.findByEmail(req.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("Email already in use: " + req.getEmail());
        }

        //create password hashing strategy and then add it to db by using setter
        String userPassword = req.getPassword();
        String encodedPassHash = passwordEncoder.encode(userPassword);

        User newUser = new User(req.getEmail(),encodedPassHash);
        User saved = repo.save(newUser);

        //generate jwt for this new user
        String token = jwtTokenProvider.generateToken(saved.getId(), saved.getEmail());


        return new AuthResponse(token, saved.getId(), saved.getEmail()); //gives controller data
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest req) {
        // 1) Load user
        User user = repo.findByEmail(req.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("No user with email " + req.getEmail()));

        // 2) Verify password
        if (!passwordEncoder.matches(req.getPassword(), user.getPassHash())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        // 3) Issue token
        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail());

        // 4) Return DTO
        return new AuthResponse(token, user.getId(), user.getEmail());
    }


}
