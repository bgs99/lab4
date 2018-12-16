package bgs.controllers;

import bgs.model.Login;
import bgs.repo.LoginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginsRepository loginsRepository;

    @RequestMapping("/register")
    public boolean registerUser(@RequestParam(name = "username") String name,
                          @RequestParam(name = "password") String pass){
        if(loginsRepository.existsById(name))
            return false;
        Login l = new Login(name, pass);
        loginsRepository.save(l);
        return true;
    }
}