package com.example.onlinehotelbookingsystem.service.impl;

//import com.example.onlinehotelbookingsystem.config.StripeConfigurationService;

import com.example.onlinehotelbookingsystem.model.entity.BookingEntity;
import com.example.onlinehotelbookingsystem.model.entity.PaymentEntity;
import com.example.onlinehotelbookingsystem.repository.BookingRepository;
import com.example.onlinehotelbookingsystem.repository.PaymentRepository;
import com.example.onlinehotelbookingsystem.service.StripeService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum.PAID;

@Service
public class StripeServiceImpl implements StripeService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
//    private final StripeConfigurationService stripeConfigurationService;

    @Value("${stripe.keys.secret}")
    private String API_SECRET_KEY;

    public StripeServiceImpl(PaymentRepository paymentRepository,
                             BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
//        this.stripeConfigurationService = stripeConfigurationService;
    }


//    In general, you can store any data returned by the API's.
//    The important thing is that you're not letting user's payment information
//    (card number, bank numbers, etc.) touch your database and only working with their tokens / ids.
//    As for what you store for your own use, there's not really a "best practice", other than don't store data you don't really need.


    @Override
    public String createCharge(String email, String token, int amount, Long bookingId) {
        String id = null;
        try {
            Stripe.apiKey = API_SECRET_KEY;
//            Stripe.apiKey = this.stripeConfigurationService.getAPI_SECRET_KEY();
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount);
            chargeParams.put("currency", "eur");
            chargeParams.put("description", "Charge for " + email);
            chargeParams.put("source", token); //  obtained with Stripe.js

            //create a charge
            Charge charge = Charge.create(chargeParams);
            id = charge.getId();
            setPayment(bookingId, id);
//            savePaymentId(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;

    }

    private void setPayment(Long bookingId, String id) {
        BookingEntity bookingEntity = this.bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new ObjectNotFoundException("Booking with id " + bookingId + "not found!"));
        PaymentEntity payment = bookingEntity.getPayment();
        payment
                .setStatusEnum(PAID)
                .setChargeId(id);
        this.paymentRepository.save(payment);
        System.out.println();
    }

}
