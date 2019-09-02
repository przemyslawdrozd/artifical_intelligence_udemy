package com.company.ai_in_games_1.particle_swarm_optimization;

import static com.company.ai_in_games_1.particle_swarm_optimization.Constants.*;

public class ParticleSwarmOptimization {

    private double[] bestGlobalSolutions;
    private Particle[] particleSwarm;
    private int epochs;

    public ParticleSwarmOptimization() {
        this.bestGlobalSolutions = new double[Constants.NUM_DIMENSIONS];
        this.particleSwarm = new Particle[Constants.NUM_PARTICLES];
        generateRandomSolution();
    }

    private void generateRandomSolution() {
        for (int i = 0; i < Constants.NUM_DIMENSIONS; ++i) {
            double randomCoordinate = random(Constants.MIN, Constants.MAX);
            this.bestGlobalSolutions[i] = randomCoordinate;
        }
    }

    public void solve() {
        // Init the particles
        for (int i = 0; i < Constants.NUM_PARTICLES; ++i) {
            double[] locations = initializeLocation();
            double[] velocity = initializeVelocity();
            this.particleSwarm[i] = new Particle(locations, velocity);
        }

        while (epochs < Constants.MAX_ITERATIONS) {
            for (Particle actualParticle: this.particleSwarm) {

                // Updating the velocities
                for (int i = 0; i < actualParticle.getVelocity().length; ++i) {
                    double rp = Math.random();
                    double rg = Math.random();

                    actualParticle.getVelocity()[i] = W * actualParticle.getVelocity()[1]
                    + C1 * rp * (actualParticle.getBestPosition()[i] - actualParticle.getPosition()[i]
                    + C2 * rg * (this.bestGlobalSolutions[i] - actualParticle.getPosition()[i]));
                }

                // Update the actual position
                for (int i = 0; i < actualParticle.getPosition().length; ++i) {
                    actualParticle.getPosition()[i] += actualParticle.getVelocity()[i];

                    if (actualParticle.getPosition()[i] < MIN) {
                        actualParticle.getPosition()[i] = MIN;
                    } else if (actualParticle.getPosition()[i] > MAX) {
                        actualParticle.getPosition()[i] = MAX;
                    }
                }

                if (f(actualParticle.getPosition()) > f(actualParticle.getBestPosition())) {
                    actualParticle.setBestPosition(actualParticle.getPosition());
                }

                if (f(actualParticle.getBestPosition()) > f(bestGlobalSolutions)) {
                    System.arraycopy(actualParticle.getBestPosition(),
                            0,
                            bestGlobalSolutions,
                            0,
                            actualParticle.getBestPosition().length);
                }
            }

            this.epochs++;
        }
    }

    public double[] initializeLocation() {
        double x = random(Constants.MIN, Constants.MAX);
        double y = random(Constants.MIN, Constants.MAX);

        double[] newLocation = new double[] {x, y};
        return newLocation;
    }

    private double[] initializeVelocity() {
        double vx = random(-(Constants.MAX - Constants.MIN), (Constants.MAX - Constants.MIN));
        double vy = random(-(Constants.MAX - Constants.MIN), (Constants.MAX - Constants.MIN));

        double[] newVelocity = new double[] {vx, vy};
        return newVelocity;
    }

    private double random(double min, double max) {
        return min + (max - min) * Math.random();
    }

    public int getEpochs() {
        return epochs;
    }

    public void showSolution() {
        System.out.println("Solution for PSO problem!");
        System.out.println("Best Solution is: x: " + this.bestGlobalSolutions[0] + "\n" +
                "y: " + this.bestGlobalSolutions[1]);
        System.out.println("Value f(x, y) = " + Constants.f(this.bestGlobalSolutions));
    }
}












