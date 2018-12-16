package bgs.model;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Login implements Serializable {
    @Id
    private String name;
    private String password;
    protected Login(){};
    public Login(String name, String pass){
        this.name = name;
        this.password = new BCryptPasswordEncoder().encode(pass);
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
}
