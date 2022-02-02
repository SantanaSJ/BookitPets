package com.example.onlinehotelbookingsystem.model.entity;

import com.example.onlinehotelbookingsystem.model.entity.enums.PaymentStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class InvoiceEntity extends BaseEntity {

    @ManyToOne
    private UserEntity guest;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;

    public InvoiceEntity() {
    }

    public UserEntity getGuest() {
        return guest;
    }

    public InvoiceEntity setGuest(UserEntity guest) {
        this.guest = guest;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public InvoiceEntity setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public InvoiceEntity setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public PaymentStatusEnum getStatus() {
        return status;
    }

    public InvoiceEntity setStatus(PaymentStatusEnum status) {
        this.status = status;
        return this;
    }
}
