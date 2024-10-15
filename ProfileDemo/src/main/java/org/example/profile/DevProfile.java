package org.example.profile;

public class DevProfile implements EnvProfile {

    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
