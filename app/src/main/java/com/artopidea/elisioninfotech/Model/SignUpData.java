package com.artopidea.elisioninfotech.Model;

public class SignUpData {

    String message;
    String status;

    public SignUpData() {
    }

    public SignUpData(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
