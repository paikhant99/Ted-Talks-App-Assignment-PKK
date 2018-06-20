package com.padcmyanmar.ted_talks_app_assignment_pkk.events;

/**
 * Created by paikhantko on 6/19/18.
 */

public class ErrorApiEvent {
    private String message;

    public ErrorApiEvent(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
