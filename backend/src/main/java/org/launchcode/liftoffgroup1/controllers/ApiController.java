package org.launchcode.liftoffgroup1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/signUp")
    public String displayUserSignUp () {
        return "signUp/index";
    }

}
