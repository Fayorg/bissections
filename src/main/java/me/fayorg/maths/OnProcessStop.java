package me.fayorg.maths;

import org.apfloat.Apfloat;

public class OnProcessStop extends Thread {

    public Apfloat x1, x2, middle;
    public int iteration = 0;
    private int maxDepth = 0;

    public OnProcessStop(int maxIteration) {
        this.maxDepth = maxIteration;
    }

    @Override
    public void run() {
        System.err.println("Shutting down on iteration " + iteration + " of " + maxDepth + " !");
        System.out.println("Current interval is [" + x1.toString(true) + ";" + x2.toString(true) + "]");
        System.out.println("Current middle is " + this.middle.toString(true));
    }
}
