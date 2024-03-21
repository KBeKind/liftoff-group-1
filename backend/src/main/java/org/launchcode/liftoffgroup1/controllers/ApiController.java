package org.launchcode.liftoffgroup1.controllers;

import org.launchcode.liftoffgroup1.model.User;
import org.launchcode.liftoffgroup1.model.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @PostExchange("/users")
    public User handleNewUserForm (@RequestParam String newUserRequest) {
        User user = (User) (Object) newUserRequest;
        userRepository.save(user);
        return user;
    }
    //post
    //get
    //put
    //delete
}
