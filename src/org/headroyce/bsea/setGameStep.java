package org.headroyce.bsea;

public class setGameStep extends setValues {
    public setGameStep(boolean run) {
        super(GameLogic.GAME_STEP_TIMER, 17.0, run, """
                            Enter how fast you want the game to be, as a percentage.
                                
                            100 (%) is normal game speed. 
                            You can enter any value with the following domain restrictions:
                            -126322567 < x < 126322567 (java's min and max values divided by 17, the default tempo)
                            In other words, 'any' value in a practical sense. 
                            
                Default is 17 milliseconds because that means 58.82 or ~60 firings per second, which is the speed of 
                the presumed 60 fps graphics ability of your computer. 
                Therefore, every 17 milliseconds, the rendering aspect of the program 'does stuff.'
                If you make the value less than 1000/60, or 16 and 2/3, there will be no benefit to your graphics.
                For example, if you make this value, GAME_STEP_TIMER, equal to 0.0001, 
                the program *may* run up to 10,000,000 times per second, 
                but your graphics will be '10,000,000K resolution'. They will still be at 60 fps. 
                However, the 'tempo' will pick up. 
                The player will become more responsive to your key inputs, 
                and the enemies will move faster. This is because the 'move' method, 
                which moves things by incrementing their x,y coordinates, fires every game step, 
                which you have now set to occur 10,000,000 times per second. 
                Set it even lower, and the tempo is now up to the efficiency of the program.
                The smarter the code, the faster. 
                For instance, if you ask the game step to happen 10 billion times per second, 
                the program likely won't keep up.

                                
                            
                The recommended range is between 50% and 1700%.
                                        
                                
                            """);
    }
}
