package com.ai.controller;

import com.ai.domain.User;
import com.ai.repository.mongo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public Mono<User> saveUser(@RequestBody User user) {
        log.info("Inside saving user");
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error while saving user !!!");
            return Mono.just(new User());
        }
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        // log.info("Inside getAllUsers()");
        return userRepo.findAll();
    }

    @DeleteMapping
    public Mono<String> deleteUser(@RequestParam("id") String id) {
        log.info("deleteUser()");
        try {
            userRepo.deleteById(id).subscribe();
            return Mono.just("User Delete Successfully..");
        } catch (Exception e) {
            e.printStackTrace();
            // log.info("Error while deleting user !!!");
        }
        return Mono.just("Error while deleting user !!!");
    }

    @PutMapping
    public Mono<User> updateUser(@RequestBody User user) {
        log.info("updateUser()");
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error while updating user !!!");
        }
        return null;
    }

}
