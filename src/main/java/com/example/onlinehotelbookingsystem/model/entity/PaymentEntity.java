package com.example.onlinehotelbookingsystem.model.entity;

import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class PaymentEntity extends BaseEntity {

    private String chargeId;

    private PaymentStatusEnum statusEnum;

    public PaymentEntity() {
    }


    public String getChargeId() {
        return chargeId;
    }

    public PaymentEntity setChargeId(String chargeId) {
        this.chargeId = chargeId;
        return this;
    }

    public PaymentStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public PaymentEntity setStatusEnum(PaymentStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
        return this;
    }
}
