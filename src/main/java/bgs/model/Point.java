package bgs.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Point implements Serializable {
    @Id
    @SequenceGenerator(name="SEQ_GEN", sequenceName="point_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")//"node build/build.js dev"
    private int id;
    private double x;
    private double y;
    private double r;
    private String usersession;
    private boolean inside;
    protected Point(){}
    public Point(double x, double y, double r, String session){
        this.x = x;
        this.y = y;
        this.r = r;
        this.usersession = session;
        this.inside = checkPoint();
    }
    private boolean checkPoint(){
        if(x > 0 && y > 0)
            return false;
        if(x >= 0 && y <= 0)
            return y >= x-r/2;
        if(x <= 0 && y >= 0)
            return x*x +y*y < r*r;
        return x > -r/2 && y > -r;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public int getId() {
        return id;
    }

    public double getR() {
        return r;
    }

    public boolean isInside() {
        return inside;
    }

    public String getUsersession() {
        return usersession;
    }
}
