package com.example.newRestAPI.restAPI.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {
    UserDAOService userDAOService;

    public UserResource(UserDAOService userDAOService) {
        this.userDAOService = userDAOService;
    }

    @GetMapping("/Users")
    public List<User> retrieveAllUsers(){
      return   userDAOService.findAll();

    }
//    @GetMapping("/Users/{id}")
//    public User retrieveUser(@PathVariable int id){
//        User user= userDAOService.findOne(id);
//        if (user==null){
//            throw new userNotFoundException("id:"+id);
//        }
//        return user;
//
//    }
    @GetMapping("/Users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user= userDAOService.findOne(id);
        if (user==null){
            throw new userNotFoundException("id:"+id);
        }
        EntityModel<User> entityModel  = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;

    }
    @DeleteMapping ("/Users/{id}")
    public void deleteUser(@PathVariable int id){
         userDAOService.deleteById(id);
    }
    @PostMapping("/Users")
    public ResponseEntity<User> saveUser( @Validated @RequestBody User user){
       User savedUser= userDAOService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
