package com.artopidea.elisioninfotech.Model;

public class LogInModel {

    String message;
    String status;
    LogInData data = null;

    public LogInModel() {
    }

    public LogInModel(String message, String status, LogInData data) {
        this.message = message;
        this.status = status;
        this.data = data;
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

    public LogInData getLogInData() {
        return data;
    }

    public void setLogInData(LogInData data) {
        this.data = data;
    }
}
