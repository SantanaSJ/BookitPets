package com.example.onlinehotelbookingsystem.model.entity;

import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "propertyTypes")
public class AccommodationTypeEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private AccommodationTypeEnum type;

    public AccommodationTypeEntity() {
    }

    public AccommodationTypeEnum getType() {
        return type;
    }

    public AccommodationTypeEntity setType(AccommodationTypeEnum type) {
        this.type = type;
        return this;
    }
}
