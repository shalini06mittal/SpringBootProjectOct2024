package com.training.SpringBootRESTRepo.profile;

import org.example.profile.EnvProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileRestController {

    @Autowired
    private EnvProfile profile;

    @GetMapping
    public  String getMessageForProfile(){
        return profile.getMessage();
        //return  null;
    }
}
