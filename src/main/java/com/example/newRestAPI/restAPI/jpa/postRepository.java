package com.example.newRestAPI.restAPI.jpa;

import com.example.newRestAPI.restAPI.Post;
import com.example.newRestAPI.restAPI.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface postRepository extends JpaRepository<Post,Integer> {
}
