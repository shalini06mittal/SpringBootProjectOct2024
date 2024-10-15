package org.example.profile;


public class DefaultProfile implements EnvProfile {

    String message;

    public DefaultProfile() {
        System.out.println("default profile");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
