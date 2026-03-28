package com.example.newRestAPI.restAPI.jpa;

import com.example.newRestAPI.restAPI.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface userRepository extends JpaRepository<User,Integer> {
}
