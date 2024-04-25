package com.fernandoschimidt.cryptography.dto;

import com.fernandoschimidt.cryptography.entity.TransactionEntity;

public record TransactionReponse(Long id, String userDocument, String creditCardToken, Long value) {

    public static TransactionReponse fromEntity(TransactionEntity entity) {
        return new TransactionReponse(
                entity.getId(),
                entity.getRawUserDocument(),
                entity.getRawCreditCardToken(),
                entity.getTransactionValue()
        );
    }
}
