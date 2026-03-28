package com.example.newRestAPI.restAPI.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class filteringController {

    @GetMapping("/filtering")
    public someBean filtering(){
        return new someBean("value1","value2","value3");
    }
}
