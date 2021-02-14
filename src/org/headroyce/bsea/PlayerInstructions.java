package org.headroyce.bsea;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerInstructions extends Canvas {
    public PlayerInstructions(GraphicsContext graphicsContext, boolean run) {
        if (run) {
            Mob[] mobs = {new Mob(), new Obstacle(444), new SpikedWall(444, 444),
                    new Sprinkle(), new Ball(), new LifeGiver()}; //444 are irrelevant arguments
            String[] specs = {"\n\n", "\nPURPLE RECTANGLE\n", "\nBIG AQUA RECTANGLE\n", "\nTINY BALLS\n", "\nBIGGER BALLS\n",
                    "\nRED BALLS\n", "\nGREEN BALLS\n"};
            StringBuilder fullInstructions = new StringBuilder();
            for (int i = 0; i < mobs.length; i++) {
                Mob mob = mobs[i];
                Color color = null;
                if (!(mob instanceof Sprinkle)) {
                    color = mob.getColor();
                }
                String specifications =
                        (specs[i] + "\nClass of Mob: " + mob.getClass() + "\n" +
                                "Color: " + color + "\n" +
                                "Damage: " + mob.getDamage() + " -1,2,3 hurts you, +1 helps you.\n" +
                                "The Mob is destroyable: " + mob.isDestroyable() + "\n" +
                                "offPoints: " + mob.getOffPoints() + " = how many points you get when the Mob leaves the screen.");
                fullInstructions.append(specifications);
            }

//        // Draw lives and score last so that the balls go under them
//        Text lives = new Text("Lives: " + Math.round(player.getHP()));
//
//        gc.strokeText("Score: " + playerScore, 10, 30);
//        gc.strokeText(lives.getText(), width - 10 - lives.getLayoutBounds().getWidth(), 20);
            graphicsContext.strokeText(fullInstructions.toString(), 20, 40);
        }
    }
}
