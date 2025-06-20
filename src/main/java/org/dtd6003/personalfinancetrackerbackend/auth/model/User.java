package org.dtd6003.personalfinancetrackerbackend.auth.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name="pass_hash", nullable=false)
    private String passHash;

    @CreationTimestamp
    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;


    protected User(){};

    //declare construtor for beans
    public User(String email, String passHash){
        this.email = email;
        this.passHash = passHash;
    }

    //set getters
    public Long getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassHash(){
        return this.passHash;
    }

    public LocalDateTime getCreatedAt(){
        return this. createdAt;
    }

    //set setters
    public void setEmail(String email){
        this.email = email;
    }

    //im assuming generate password hash function here?
    public void setPassHash(String passHash){
        this.passHash = passHash;
    }
}
