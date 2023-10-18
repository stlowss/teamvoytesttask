package com.example.teamvoytesttask.exception;

public class ItemAlreadyInOrderException extends RuntimeException{
    public ItemAlreadyInOrderException(String message) {
        super(message);
    }
}
