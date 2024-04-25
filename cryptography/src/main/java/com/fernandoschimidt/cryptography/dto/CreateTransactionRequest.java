package com.fernandoschimidt.cryptography.dto;

public record CreateTransactionRequest(String creditCardToken, String userDocument, Long value) {
}
