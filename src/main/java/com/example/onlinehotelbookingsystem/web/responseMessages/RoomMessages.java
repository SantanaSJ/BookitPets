package com.example.onlinehotelbookingsystem.web.responseMessages;

import java.util.ArrayList;
import java.util.List;

public class RoomMessages {

    private List<String> type = new ArrayList<>();
    private List<String> noRoomsMessage = new ArrayList<>();
    private List<String> successMessage = new ArrayList<>();


    public List<String> getMessage() {
        return noRoomsMessage;
    }

    public RoomMessages setMessage(List<String> message) {
        this.noRoomsMessage = message;
        return this;
    }

    public List<String> getType() {
        return type;
    }

    public RoomMessages setType(List<String> type) {
        this.type = type;
        return this;
    }

    public List<String> getNoRoomsMessage() {
        return noRoomsMessage;
    }

    public RoomMessages setNoRoomsMessage(List<String> noRoomsMessage) {
        this.noRoomsMessage = noRoomsMessage;
        return this;
    }

    public List<String> getSuccessMessage() {
        return successMessage;
    }

    public RoomMessages setSuccessMessage(List<String> successMessage) {
        this.successMessage = successMessage;
        return this;
    }
}
