package org.headroyce.bsea;

public class set_OBSTACLE_SPAWN_PROBABILITY extends setValues {
    public set_OBSTACLE_SPAWN_PROBABILITY(boolean run) {
        super(GameLogic.OBSTACLE_SPAWN_PROBABILITY, 10, run,
                """
                        Set the rate of spawn of obstacles, as a percent. 
                                       
                        100% will set it to the default of 10. Don't worry about the units of '10'
                         
                        - if you really want to know, it means that:
                         10% of the times when time_elapsed is less than GAME_STEP_TIMER, 
                         and time_elapsed is less than ENEMY_SPAWN_TIMER, *minus* ENEMY_SPAWN_PROBABILITY, an obstacle will spawn.
                         Told ya you did not want to know the units. 
                          time_elapsed is how long the program has been running 
                         after said trigger, at which time it resets to 0, and GAME_STEP_TIMER is at default 17 milliseconds. 
                         see setGameStep.java for more info on the GAME_STEP_TIMER. 
                         see set_ENEMY_SPAWN_TIMER for more info on ENEMY_SPAWN_TIMER
                         see set_ENEMY_SPAWN_PROBABILITY for more info on ENEMY_SPAWN_PROBABILITY.
                         
                         The default is 10%, so if you enter 100%, you will get 10%. 
                         Sorry for the double %.
                         Keep in mind, do not enter anything over 1000%, 
                         which equates to obstacles spawning 100% of obstacle spawn times, 
                         and >1000% means >100% chance of spawning. Not possible! 
                         
                        """);
    }
}
