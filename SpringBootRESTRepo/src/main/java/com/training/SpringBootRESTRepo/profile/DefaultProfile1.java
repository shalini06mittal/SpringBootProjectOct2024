package com.training.SpringBootRESTRepo.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class DefaultProfile1 implements EnvProfile1 {


    @Value("${message}")
    String message;

    public DefaultProfile1() {
        System.out.println("default profile");
    }

    @Override
    public String message() {
        return message;
    }
}
