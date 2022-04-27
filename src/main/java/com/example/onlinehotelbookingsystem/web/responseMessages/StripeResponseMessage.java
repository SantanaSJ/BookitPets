package com.example.onlinehotelbookingsystem.web.responseMessages;

public class StripeResponseMessage {
    //????
    private boolean status;
    private String details;

    public StripeResponseMessage(boolean status, String details) {
        this.status = status;
        this.details = details;
    }

    public boolean isStatus() {
        return status;
    }

    public StripeResponseMessage setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public StripeResponseMessage setDetails(String details) {
        this.details = details;
        return this;
    }
}
