package com.fernandoschimidt.cryptography.controller;

import com.fernandoschimidt.cryptography.dto.CreateTransactionRequest;
import com.fernandoschimidt.cryptography.dto.TransactionReponse;
import com.fernandoschimidt.cryptography.dto.UpdateTransactionRequest;
import com.fernandoschimidt.cryptography.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody CreateTransactionRequest request
    ) {
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionReponse>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        var body = service.listAll(page, size);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionReponse> findById(@PathVariable(name = "id") Long id) {
        var body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    //    @PutMapping("/{id}")
//    public ResponseEntity<Void> update(
//            @PathVariable(name = "id") Long id,
//            @RequestBody CreateTransactionRequest request
//    ) {
//        service.update(id, request);
//        return ResponseEntity.ok().build();
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateTransactionRequest request
    ) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
