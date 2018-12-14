package bgs.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Point implements Serializable {
    @Id @GeneratedValue
    private int id;
    private double x;
    private double y;
    private double r;
    private String session;
    private boolean inside;
    protected Point(){};
    public Point(double x, double y, int r, String session){
        this.x = x;
        this.y = y;
        this.r = r;
        this.session = session;
        checkPoint();
    }
    private void checkPoint(){
        inside = false;
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

    public String getSession() {
        return session;
    }
}
