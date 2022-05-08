package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.AccommodationTypeEntity;
import com.example.onlinehotelbookingsystem.model.entity.enums.AccommodationTypeEnum;
import com.example.onlinehotelbookingsystem.repository.AccommodationTypeRepository;
import com.example.onlinehotelbookingsystem.service.AccommodationTypeService;
import org.springframework.stereotype.Service;

@Service
public class AccommodationTypeServiceImpl implements AccommodationTypeService {

    private final AccommodationTypeRepository accommodationTypeRepository;

    public AccommodationTypeServiceImpl(AccommodationTypeRepository propertyTypeRepository) {
        this.accommodationTypeRepository = propertyTypeRepository;
    }


    @Override
    public AccommodationTypeEntity findByAccommodationType(AccommodationTypeEnum typeEnum) {
        AccommodationTypeEntity byType = this.accommodationTypeRepository.findByType(typeEnum);
        return byType;
    }

    @Override
    public void initAccommodationTypes() {

        if (this.accommodationTypeRepository.count() == 0) {
            for (AccommodationTypeEnum type : AccommodationTypeEnum.values()) {
                AccommodationTypeEntity accommodationTypeEntity = new AccommodationTypeEntity();
                accommodationTypeEntity.setType(type);
                this.accommodationTypeRepository.save(accommodationTypeEntity);
            }
        }
    }


}
