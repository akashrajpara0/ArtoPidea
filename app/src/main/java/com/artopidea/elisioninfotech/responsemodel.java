package com.artopidea.elisioninfotech;

public class responsemodel {

    String message;
    String status;

    public responsemodel() {
    }

    public responsemodel(String message, String status) {
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
