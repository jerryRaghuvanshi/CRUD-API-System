package com.example.newRestAPI.restAPI.user;

import com.example.newRestAPI.restAPI.Post;
import com.example.newRestAPI.restAPI.jpa.postRepository;
import com.example.newRestAPI.restAPI.jpa.userRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    userRepository repository;
    postRepository postRepository;

    public UserJpaResource(userRepository repository, postRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/Users")
    public List<User> retrieveAllUsers(){
      return   repository.findAll();

    }
    @GetMapping("/jpa/Users/{id}")
    public List<Post> retrievePostForUser(@PathVariable int id){
        Optional<User> user= repository.findById(id);
        if (user.isEmpty()){
            throw new userNotFoundException("id:"+id);
        }
        return   user.get().getPosts();

    }

    @GetMapping("/jpa/Users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user= repository.findById(id);
        if (user.isEmpty()){
            throw new userNotFoundException("id:"+id);
        }
        EntityModel<User> entityModel  = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;

    }
    @DeleteMapping ("/jpa/Users/{id}")
    public void deleteUser(@PathVariable int id){
         repository.deleteById(id);
    }
    @PostMapping("/jpa/Users")
    public ResponseEntity<User> saveUser( @Validated @RequestBody User user){
       User savedUser= repository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
