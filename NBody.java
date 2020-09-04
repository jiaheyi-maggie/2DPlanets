public class NBody {
    /** Read radius from txt files (input stream). */
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int countBody = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** Read bodies' information and put 5 Body objects in an array. */
    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int countBody = in.readInt();
        double radius = in.readDouble();
        Body[] bodyArr = new Body[countBody];

        for (int i = 0; i < countBody; i++){
            bodyArr[i] = new Body(0,0,0,0,0,"");
            bodyArr[i].xxPos = in.readDouble();
            bodyArr[i].yyPos = in.readDouble(); 
            bodyArr[i].xxVel = in.readDouble();
            bodyArr[i].yyVel = in.readDouble();
            bodyArr[i].mass = in.readDouble();
            bodyArr[i].imgFileName = in.readString();
        }
        return bodyArr;
    }

    public static void main(String[] args){ //String[] contains commands in terminal
        /** Get body information. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        int countBody = bodies.length;

        /** Drawing set up. */
        StdDraw.enableDoubleBuffering();
        /** Draw all the planets with the proper setup of the background. */
        double time = 0;
        double[] xForces = new double[countBody];
        double[] yForces = new double[countBody];
        while (time < T){
            /** Draw Background. */
            /** P.S. I just snitched and told Stephen Hawkings 
             * that this is discrete time. */
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i = 0; i < countBody; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < countBody; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
                bodies[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time += dt;            
        }

        /** Print out the universe. */
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
                            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }

}