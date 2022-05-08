package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.entity.AccommodationTypeEntity;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;

public interface AccommodationTypeService {

    AccommodationTypeEntity findByAccommodationType(AccommodationTypeEnum typeEnum);

    void initAccommodationTypes();
}
