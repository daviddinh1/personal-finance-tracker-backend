package org.dtd6003.personalfinancetrackerbackend.transaction.model;

import jakarta.persistence.*;
import org.dtd6003.personalfinancetrackerbackend.auth.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // maps to user_id FK
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="type", nullable=false)
    private String type;

    @Column(name="description")
    private String description;

    @Column(name="amount", precision=12, scale=2, nullable=false)
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;

    protected Transaction(){}

    public Transaction(User user,String type, String description, BigDecimal amount){
        this.user = user;
        this.type = type;
        this.description = description;
        this.amount = amount;
    }


    //set getters and setters
    public Long getId(){
        return this.id;
    }

    public User getUser(){
        return this.user;
    }

    public String getType(){
        return this.type;
    }

    public String getDescription(){
        return this.description;
    }

    public BigDecimal getAmount(){
        return this.amount;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
