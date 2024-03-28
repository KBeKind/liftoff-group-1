package org.launchcode.liftoffgroup1.controllers;

import org.apache.coyote.Response;
import org.launchcode.liftoffgroup1.model.FormUser;
import org.launchcode.liftoffgroup1.model.User;
import org.launchcode.liftoffgroup1.model.data.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<String> createNewUser (@RequestBody FormUser newUserRequest) {
        FormUser user = (FormUser) (Object) newUserRequest;
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        userRepository.save(newUser);
        return ResponseEntity.ok("User Created");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> fetchUserData (@PathVariable int id) {
        User fetchedUser = userRepository.findById(id).get();
        return ResponseEntity.ok(fetchedUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUserData (@PathVariable int id, @RequestBody(required = false) String name, @RequestBody(required = false) String email) {
        User fetchedUser = userRepository.findById(id).get();
        if (name.isBlank() && email.isBlank()) {
            return ResponseEntity.ok("No data entered");
        } else if (!name.isBlank() && email.isBlank()) {
            fetchedUser.setName(name);
            return ResponseEntity.ok("Username changed");
        } else if (name.isBlank() && !email.isBlank()) {
            fetchedUser.setEmail(email);
            return ResponseEntity.ok("Email changed");
        } else {
            fetchedUser.setName(name);
            fetchedUser.setEmail(email);
            return ResponseEntity.ok("Username and Email changed");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserData (@PathVariable int id, @RequestBody String verify) {
        if (userRepository.findById(id).isPresent() && verify.equals("true")) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User successfully deleted");
        } else {
            return ResponseEntity.ok("User deletion denied");
        }
    }
}
