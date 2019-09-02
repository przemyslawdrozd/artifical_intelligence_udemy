package com.company.ai_in_games_1.particle_swarm_optimization;

public class TestParticleSwarm {

    public static void main(String[] args) {
        ParticleSwarmOptimization particleSwarmOptimization = new ParticleSwarmOptimization();

        particleSwarmOptimization.solve();
        particleSwarmOptimization.showSolution();
        System.out.println(particleSwarmOptimization.getEpochs());
    }
}
