package com.example.onlinehotelbookingsystem.repository;

import com.example.onlinehotelbookingsystem.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {


//    PaymentEntity findPaymentEntitiesByStatusEnumEquals(PaymentStatusEnum valueOf);
}
