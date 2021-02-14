package org.headroyce.bsea;

public class set_ENEMY_SPAWN_PROBABILITY extends setValues {
    public set_ENEMY_SPAWN_PROBABILITY(boolean run) {
        super(GameLogic.ENEMY_SPAWN_PROBABILITY, 5, run, """
                Set the spawn rate of ROUND enemies, as a percent. 
                                
                Sorry for double %, but the default is 5%, 
                so if you want default, ENTER 100%!!!! 
                Entering 5% will give you 5% of 5%, or 0.25% spawn rate. 
                                
                This is affected by ENEMY_SPAWN_TIME/_TIMER. 
                If this is the default 150 milliseconds,
                5% of these checks will generate an obstacle. 
                For reference, the program generates a pseudorandom number between 0 and 100, 
                and checks if it is less than the given probability, e.g., 5. 
                Naturally, 5% of random numbers between 0 and 100 are less than 5, 
                so you get pseudorandomness at any given point - think a whole wave of enemies - 
                but predictability over a long period.
                                
                150 milliseconds / 0.05 = 3000 milliseconds, 3 seconds.
                                
                The less frequent the checks, the fewer enemies. 
                Simultaneously, the lower probability IN those checks, the fewer enemies.
                                
                There's more! This affects OBSTACLE_SPAWN_PROBABILITY. 
                The logic governing spawning is all `if` or `if-else` statements:
                //selected code from GameLogic.java
                            if (ENEMY_SPAWN_TIMER < 0) {
                                 int chance = rand.nextInt(100); //make a random number 0-100
                                 if (chance < OBSTACLE_SPAWN_PROBABILITY) {
                                    /*IF it's less than default 10, 
                                    meaning 10 out of 100, 1 out of 10, 10% of the time
                                    THEN move on to more stuff.
                                    */
                                     if (chance < ENEMY_SPAWN_PROBABILITY) {
                                     //IF it's less than default 5, then create circular enemies, a.k.a. balls.
                                     }
                                     else {
                                     //create rectangular enemies, a.k.a. obstacles
                                     }
                                }
                            }
                Therefore, if a pseudorandom number, x, < 10, 
                half the time x < 5, in which case balls spawn. 
                The remaining time, x > 5, then obstacles spawn. 
                The program does not deal with x = 5 exactly. 
                This is unlikely and irrelevant given the precision -  x can be 4.99999, for example.
                                
                So if you increase ENEMY_SPAWN_PROBABILITY without increasing OBSTACLE_SPAWN_PROBABILITY, 
                you will get a greater proportion of balls to obstacles.
                                
                Do not enter anything over 2000%, 
                which would signify that balls should spawn >100% of the time.
                                
                                
                             
                """);
    }
}
