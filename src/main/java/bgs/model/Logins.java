package bgs.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Logins implements Serializable {
    @Id
    private String name;
    private String password;
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
}
