package com.training.SpringBootRESTRepo;

import org.example.profile.DefaultProfile;
import org.example.profile.DevProfile;
import org.example.profile.ProductionProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class AppConfigs {

    public AppConfigs() {
        System.out.println("App Configs loaded");
    }

    @Value("${message}")
    private String message;
    @Bean
    @Profile("dev")
    public DevProfile devProfile(){
        DevProfile profile = new DevProfile();
        profile.setMessage(message);
        return  profile;
    }
    @Bean
    @Profile("prod")
    public ProductionProfile prodProfile(){
        ProductionProfile profile = new ProductionProfile();
        profile.setMessage(message);
        return  profile;
    }
    @Bean
    @Profile("default")
    public DefaultProfile defaultProfileProfile(){
        DefaultProfile profile = new DefaultProfile();
        profile.setMessage(message);
        return  profile;
    }
}
