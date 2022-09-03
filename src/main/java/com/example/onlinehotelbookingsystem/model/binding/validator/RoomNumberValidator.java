package com.example.onlinehotelbookingsystem.model.binding.validator;

import com.example.onlinehotelbookingsystem.model.binding.RoomBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class RoomNumberValidator implements ConstraintValidator<ValidateRoomNumber, List<RoomBindingModel>>{
    @Override
    public boolean isValid(List<RoomBindingModel> value, ConstraintValidatorContext context) {

        boolean flag = true;
        for (RoomBindingModel room : value) {
            if (room.getNumberOfRooms() == 0) {
                flag = false;
            } else {
                flag = true;
                break;
            }
        }
        return flag;
    }


    //    @Override
//    public boolean isValid(Integer value, ConstraintValidatorContext context) {
//        if (value == 0) {
//            return false;
//        }
//        return true;
//    }
}
