package org.headroyce.bsea;

public class set_ENEMY_SPAWN_TIME extends setValues {
    public set_ENEMY_SPAWN_TIME(boolean run) {
        super(GameLogic.ENEMY_SPAWN_TIME, 150, run, """
                set the ENEMY_SPAWN_TIME as a percent. 
                100% = default 150 milliseconds.
                """);
    }

}
