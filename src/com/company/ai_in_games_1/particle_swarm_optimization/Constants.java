package com.company.ai_in_games_1.particle_swarm_optimization;

public class Constants {

    public static final int MAX_ITERATIONS = 10000;

    private Constants(){}

    public static final int NUM_DIMENSIONS = 2;
    public static final int NUM_PARTICLES = 10;
    public static final double MIN = -2;
    public static final double MAX = 2;
    public static final double W = 0.729;
    public static final double C1 = 1.49;
    public static final double C2 = 1.49;

    // Function that we are looking for
    public static double f(double[] data) {
        return Math.exp(- data[0] * data[0] - data[1] * data[1]) * Math.sin(data[0]);
    }

}
