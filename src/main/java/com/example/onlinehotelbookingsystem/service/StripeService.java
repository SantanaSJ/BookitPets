package com.example.onlinehotelbookingsystem.service;

public interface StripeService {

    String createCharge(String email, String token, int amount, Long bookingId);
}
