package org.headroyce.bsea;

public class Setter {
    public Setter
            (
                    boolean set_ENEMY_DIRECTION_PROBABILITY,
                    boolean set_ENEMY_SPAWN_PROBABILITY,
                    boolean set_OBSTACLE_SPAWN_PROBABILITY,
                    boolean set_ENEMY_SPAWN_TIME,
                    boolean setGameStep
            ) {
        new set_ENEMY_DIRECTION_PROBABILITY(set_ENEMY_DIRECTION_PROBABILITY);
        new set_ENEMY_SPAWN_PROBABILITY(set_ENEMY_SPAWN_PROBABILITY);
        new set_OBSTACLE_SPAWN_PROBABILITY(set_OBSTACLE_SPAWN_PROBABILITY);
        new set_ENEMY_SPAWN_TIME(set_ENEMY_SPAWN_TIME);
        new setGameStep(setGameStep);
    }
}
