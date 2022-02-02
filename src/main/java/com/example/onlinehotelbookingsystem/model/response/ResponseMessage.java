package com.example.onlinehotelbookingsystem.model.response;

public class ResponseMessage {
//????
    private String message;


    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ResponseMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
