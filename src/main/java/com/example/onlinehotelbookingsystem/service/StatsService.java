package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
