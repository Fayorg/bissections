package me.fayorg.maths;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class Bissection {

    // Setting up the depth of the algorithm
    public static int DEPTH = 5000;

    // Setting up the interval in between the function cross the X axis
    public static Apfloat x1 = new Apfloat(-1.0, DEPTH + 1), x2 = new Apfloat(-2.0, DEPTH + 1);

    public static void main(String[] args) {

        System.out.println("Starting algorithm with the following interval : [" + x1 + ";" + x2 + "]");

        long start = System.currentTimeMillis();
        long iterationTime = System.currentTimeMillis();
        long loggingTime = System.currentTimeMillis();

        OnProcessStop stop = new OnProcessStop(DEPTH);

        Runtime.getRuntime().addShutdownHook(stop);

        for(int i = 0; i < DEPTH; i++) {
            stop.iteration = i;
            stop.x1 = x1;
            stop.x2 = x2;
            if(i % 10 == 9) {

                System.out.println("[INFO] Iteration " + (i + 1) + "/" + DEPTH + " | Time since last iteration : " + (System.currentTimeMillis() - iterationTime) + "ms | Time since last log : " + (System.currentTimeMillis() - loggingTime) + "ms | ETA : " + (System.currentTimeMillis() - iterationTime) * (DEPTH - i) / 1000 + "s");
                loggingTime = System.currentTimeMillis();

            }
            iterationTime = System.currentTimeMillis();

            // Get the current middle of the current interval
            Apfloat middle = getMiddle(x1, x2);
            stop.middle = middle;

            // Calculate result for x1, x2, middle
            Apfloat em = getResult(middle);

            // System.out.println("[INFO-" + i + "] e1 = " + e1 + " | em = " + em);

            if(isPositive(em)) {
                x2 = middle;
            } else {
                x1 = middle;
            }


            // Safest way but time-consuming
            /* if(isPositive(e1) && !isPositive(e2)) {
                if(isPositive(em)) {
                    x1 = middle;
                } else {
                    x2 = middle;
                }
            } else if(!isPositive(e1) && isPositive(e2)) {
                if(isPositive(em)) {
                    x2 = middle;
                } else {
                    x1 = middle;
                }
            } else {
                System.err.println("Error occurred with iteration : " + i);
            } */
        }

        System.out.println("Final interval is [" + x1 + ";" + x2 + "]");

        Apfloat middle = getMiddle(x1, x2);

        // System.out.println("The precision of x is " + middle.precision());
        System.out.println("Middle is " + middle.toString(true));

        System.out.println("Total Duration : " + (System.currentTimeMillis() - start) + "ms");
    }

    public static Apfloat getMiddle(Apfloat x1, Apfloat x2) {
        Apfloat middle = x1.add(x2);
        return middle.divide(new Apfloat(2));
    }

    public static Apfloat getResult(Apfloat x) {
        Apfloat twoX = x.multiply(new Apfloat(2));
        return ApfloatMath.exp(x).subtract(twoX).subtract(new Apfloat(3));
    }

    public static boolean isPositive(Apfloat n) {
        return n.signum() == 1;
    }

}
