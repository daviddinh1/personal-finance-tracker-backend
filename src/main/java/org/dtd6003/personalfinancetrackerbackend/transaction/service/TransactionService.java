package org.dtd6003.personalfinancetrackerbackend.transaction.service;

import jakarta.transaction.Transactional;
import org.dtd6003.personalfinancetrackerbackend.auth.model.User;
import org.dtd6003.personalfinancetrackerbackend.auth.repo.UserRepository;
import org.dtd6003.personalfinancetrackerbackend.transaction.dto.CreateTransactionRequest;
import org.dtd6003.personalfinancetrackerbackend.transaction.dto.TransactionResponse;
import org.dtd6003.personalfinancetrackerbackend.transaction.exceptions.ResourceNotFound;
import org.dtd6003.personalfinancetrackerbackend.transaction.model.Transaction;
import org.dtd6003.personalfinancetrackerbackend.transaction.model.TxnType;
import org.dtd6003.personalfinancetrackerbackend.transaction.repo.TransactionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository repo; //add this to use jpa repo
    private final UserRepository authRepo;

    public TransactionService(TransactionRepository repo, UserRepository authRepo){
        this.repo = repo;
        this.authRepo = authRepo;
    }

    @Transactional
    public TransactionResponse inputTransaction(CreateTransactionRequest req){
        //get userId to add into transaction table
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) auth.getPrincipal();

        if(req.getType() != TxnType.INCOME && req.getType() != TxnType.EXPENSE){
            throw new IllegalArgumentException("Type must be INCOME or EXPENSE");
        }

        //now we want to save this response to our entity

        //get table with associated FK using token
        User user = authRepo
                .findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFound("User not found with id " + userId)
                );
        Transaction newTransaction = new Transaction(user,req.getType(),req.getDescription(),req.getAmount());
        Transaction save = repo.save(newTransaction);

        return new TransactionResponse(save.getId(),userId,save.getType(),save.getAmount(),save.getDescription(),save.getCreatedAt());
    }

}
