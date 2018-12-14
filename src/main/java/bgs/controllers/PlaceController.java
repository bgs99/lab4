package bgs.controllers;

import bgs.repo.LoginsRepository;
import bgs.repo.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PlaceController {
    @Autowired
    LoginsRepository agents;
    @Autowired
    PointRepository points;

    @RequestMapping("/bcrypt")
    public String getHash(@RequestParam(name = "pass", required=false, defaultValue="password") String pass){
        return new BCryptPasswordEncoder().encode(pass);
    }

    @RequestMapping("/main")
    public String findPlace(){
        return "hi!";
    }

}