package com.example.onlinehotelbookingsystem.model.view;
//readOnly
public class StatsView {

    private final int authRequests;
    private final int anonymousRequests;

    public StatsView(int authRequests, int anonymousRequests) {
        this.authRequests = authRequests;
        this.anonymousRequests = anonymousRequests;
    }

    public int getTotalRequests() {
        return this.anonymousRequests + this.authRequests;
    }

    public int getAuthRequests() {
        return authRequests;
    }

    public int getAnonymousRequests() {
        return anonymousRequests;
    }

}
