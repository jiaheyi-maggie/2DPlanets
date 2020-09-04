import java.util.*;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // Set<Body> set = new HashSet<Body>();
    // public void addBody(Body b) {
    //     set.add(b);
    // }

    // public Iterator<Body> getBody() {
    //     return set.iterator();
    // }
    // public Iterator<Body> iterator() {
    //     return getBody();
    // }

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy,2));
    }

    public double calcForceExertedBy(Body b) {
        double r = calcDistance(b);
        double g = 6.67e-11;
        return g*b.mass*this.mass/Math.pow(r,2);
    }

    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - this.xxPos;
        double r = calcDistance(b);
        double f = calcForceExertedBy(b);
        return f*dx/r;
    }

    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - this.yyPos;
        double r = calcDistance(b);
        double f = calcForceExertedBy(b);
        return f*dy/r;
    }

    public double calcNetForceExertedByX(Body[] b) {
        double sum = 0;
        for (Body body : b) {
            if (this.equals(body)) {
                continue;
            } else {
                sum += calcForceExertedByX(body);
            }  
        }
        return sum;
    }

    public double calcNetForceExertedByY(Body[] b) {
        double sum = 0;
        for (Body body : b) {
            if (this.equals(body)) {
                continue;
            } else {
                sum += calcForceExertedByY(body);
            }  
        }
        return sum;
    }

    public void update(double dt, double fx, double fy) {
        double a_x = fx/mass;
        double a_y = fy/mass;
        xxVel += a_x * dt;
        yyVel += a_y * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;

    }

    public void draw() {
        String dir = "images/";
        StdDraw.picture(xxPos, yyPos, dir + imgFileName);
    }
}
