package com.example.newRestAPI.restAPI.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount =0;
    static {
         users.add(new User(++usersCount,"Satyam", LocalDate.now().minusYears(30)));
         users.add(new User(++usersCount,"Ashish", LocalDate.now().minusYears(25)));
         users.add(new User(++usersCount,"Munna", LocalDate.now().minusYears(45)));
    }

    public List<User> findAll(){
        return users;
    }
    public User saveUser(User user){
        user.setId(++usersCount);

         users.add(user);
        return user;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user->user.getId().equals(id);
       return users.stream().filter(predicate).findFirst().orElse(null);

    }
    public void deleteById(int id){
        Predicate<? super User> predicate = user->user.getId().equals(id);
        users.removeIf(predicate);


    }


}
