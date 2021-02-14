package org.headroyce.bsea;

public class set_ENEMY_DIRECTION_PROBABILITY extends setValues {
    public set_ENEMY_DIRECTION_PROBABILITY(boolean run) {
        super(GameLogic.ENEMY_DIRECTION_PROBABILITY, 5, run, """
                set the ENEMY_DIRECTION_PROBABILITY as a percent. 
                 100% = default 5% of the time where enemies will come'n get you!!!!""");
    }
}
