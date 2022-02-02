//package com.example.onlinehotelbookingsystem.config;
//
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.sql.Date;
//import java.time.LocalDate;
//
//@Configuration
//@Converter(autoApply = true)
//public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
//
//    @Override
//    public Date convertToDatabaseColumn(LocalDate locDate) {
//        return locDate == null ? null : Date.valueOf(locDate);
//    }
//
//    @Override
//    public LocalDate convertToEntityAttribute(Date sqlDate) {
//        return sqlDate == null ? null : sqlDate.toLocalDate();
//    }
//}
//
//
