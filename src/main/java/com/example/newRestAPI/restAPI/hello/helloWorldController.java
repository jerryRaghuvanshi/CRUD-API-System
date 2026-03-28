package com.example.newRestAPI.restAPI.hello;

import org.springframework.web.bind.annotation.*;

@RestController
public class helloWorldController {
    @RequestMapping(method = RequestMethod.GET,path = "/welcome-jay")
    public String helloWorld(){

        return "Welcome to my world Jay";
    }

    @GetMapping(path = "/welcome-jay-Bean")
    public HelloWorldBean helloWorldBean(){

        return new HelloWorldBean("My New Bean");
    }
    @GetMapping(path = "/welcome-jay/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){

        return new HelloWorldBean(String.format("My New Bean %s",name));
    }
}
