package com.example.onlinehotelbookingsystem.event;

import org.springframework.context.ApplicationEvent;

//custom event class
public class BookingCreatedEvent extends ApplicationEvent {
    private final Long id;

    public BookingCreatedEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
