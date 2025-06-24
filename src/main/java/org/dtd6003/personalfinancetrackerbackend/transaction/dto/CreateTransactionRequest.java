package org.dtd6003.personalfinancetrackerbackend.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.dtd6003.personalfinancetrackerbackend.transaction.model.TxnType;

import java.math.BigDecimal;

public class CreateTransactionRequest {
    @NotNull
    private TxnType type;

    @NotNull
    @Positive
    private BigDecimal amount;

    private String description;

    public CreateTransactionRequest(){}

    public CreateTransactionRequest(TxnType type, BigDecimal amount, String description){
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    //set dto setters and getters
    public TxnType getType(){
        return this.type;
    }
    public void setType(TxnType type){
        this.type = type;
    }

    public BigDecimal getAmount(){
        return this.amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

}
