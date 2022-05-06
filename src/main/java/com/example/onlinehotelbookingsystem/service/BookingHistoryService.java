package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.service.SummaryBookingServiceModel;
import com.example.onlinehotelbookingsystem.model.service.TitleBookingServiceModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingHistoryService {

    SummaryBookingServiceModel findCompletedBookingBy(Long id);

    List<TitleBookingServiceModel> getAllPassedBookingsBy(Long userId);

    boolean isOwnerHistory(String currentUserEmail, Long id);

    Page<SummaryBookingServiceModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

    SummaryBookingServiceModel findById(Long id);

 }
