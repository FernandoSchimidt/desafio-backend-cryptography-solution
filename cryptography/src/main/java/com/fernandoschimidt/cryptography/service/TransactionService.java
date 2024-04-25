package com.fernandoschimidt.cryptography.service;

import com.fernandoschimidt.cryptography.dto.CreateTransactionRequest;
import com.fernandoschimidt.cryptography.dto.TransactionReponse;
import com.fernandoschimidt.cryptography.dto.UpdateTransactionRequest;
import com.fernandoschimidt.cryptography.entity.TransactionEntity;
import com.fernandoschimidt.cryptography.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void create(CreateTransactionRequest request) {
        var entity = new TransactionEntity();
        entity.setRawCreditCardToken(request.creditCardToken());
        entity.setRawUserDocument(request.userDocument());
        entity.setTransactionValue(request.value());

        repository.save(entity);
    }

    public Page<TransactionReponse> listAll(int page, int size) {
        var content = repository.findAll(PageRequest.of(page, size));

        return content.map(TransactionReponse::fromEntity);
    }

    public TransactionReponse findById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TransactionReponse.fromEntity(entity);
    }

//    public void update(Long id, CreateTransactionRequest request) {
//        var oldEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        oldEntity.setRawCreditCardToken(request.creditCardToken());
//        oldEntity.setRawUserDocument(request.userDocument());
//        oldEntity.setTransactionValue(request.value());
//
//        repository.save(oldEntity);
//    }

    public void update(Long id, UpdateTransactionRequest request) {
        var entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setTransactionValue(request.value());

        repository.save(entity);
    }

    public void delete(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(entity);
    }
}
