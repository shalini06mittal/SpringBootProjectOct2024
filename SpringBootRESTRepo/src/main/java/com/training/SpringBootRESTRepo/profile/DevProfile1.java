package com.training.SpringBootRESTRepo.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevProfile1 implements EnvProfile1 {

    @Value("${message}")
    String message;
    @Override
    public String message() {
        return message;
    }
}
