package com.company.ai_in_games_1.brute_force_algorithm;

public class BruteForce {

    private static final double START_X = -1;
    private static final double END_X = 2;

    public double f(double x) {
        return (x-1) * (x-1);
    }

    public void bruteForceSearch() {

        double startingPointX = START_X;
        double min = f(startingPointX);
        double dx = 0.01;
        double minX = START_X;

        for (double i = startingPointX; i < END_X; i += dx) {
            if (f(i) > min) {
                min = f(i);
                minX = 1;
            }
        }

        System.out.println("The min value is f(x) = " + min + " and x = " + minX);
    }
}
