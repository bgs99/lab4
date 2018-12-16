package bgs.controllers;

import bgs.model.Point;
import bgs.repo.LoginsRepository;
import bgs.repo.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PointController {
    @Autowired
    PointRepository points;

    @RequestMapping("/fetch")
    public List<Point> fetchAll(){
        String session = SecurityContextHolder.getContext().getAuthentication().getName();
        return points.findAllBySession(session);
    }

    @RequestMapping("/send")
    public Point findPlace(@RequestParam("x") double x,
                            @RequestParam("y") double y,
                            @RequestParam("r") double r){
        if(x < -2 || x > 2)
            return null;
        if(y < -5 || y > 3)
            return null;
        if(r <= 0 || r > 2)
            return null;
        Point p = new Point(x, y, r, SecurityContextHolder.getContext().getAuthentication().getName());
        points.save(p);
        return p;
    }

}