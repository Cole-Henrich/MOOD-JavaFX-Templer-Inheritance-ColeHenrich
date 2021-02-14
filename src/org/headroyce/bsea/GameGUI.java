package org.headroyce.bsea;

import javafx.animation.AnimationTimer;
import javafx.geometry.Side;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

/**
 * Represents the view element of the game
 */
public class GameGUI extends StackPane {

    private final GameLogic logic;
    private final Canvas gameArea;
    private final AnimationTimer animTimer;

    private final Button1 reset;
    private final Button1 youLose;
    private final Button1 Pause;
    private final Button1 showInstructions;

    public GameGUI() {

        gameArea = new Canvas();
        gameArea.heightProperty().bind(this.heightProperty());
        gameArea.widthProperty().bind(this.widthProperty());

        animTimer = new AnimTimer();
        logic = new GameLogic(gameArea.getWidth(), gameArea.getHeight());
        reset = new Button1("Reset");
        youLose = new Button1();
        Pause = new Button1("Pause");
        showInstructions = new Button1("Show Instructions");

        Image skullImage = new Image("file:///Users/cole.henrich/Desktop/Skull&Crossbones.png");
        BackgroundPosition skullPosition = new BackgroundPosition(Side.RIGHT, .5, true, Side.BOTTOM, 0.5, true);
        BackgroundImage skullBackgroundImage = new BackgroundImage(skullImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, skullPosition, BackgroundSize.DEFAULT);
        Background skull = new Background(skullBackgroundImage);
        youLose.setBackground(skull);
        /*
       Beginning of Reset Button Creation
        */
            /*
            Beginning of sprout background creation
             */
        Image sproutImage = new Image("file:///Users/cole.henrich/Desktop/Sprout.jpg");
        BackgroundPosition sproutPosition = new BackgroundPosition(Side.RIGHT, .5, true, Side.BOTTOM, .99, true);
        BackgroundImage sproutBackgroundImage = new BackgroundImage(sproutImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, sproutPosition, BackgroundSize.DEFAULT);
        Background sprout = new Background(sproutBackgroundImage);
        reset.setBackground(sprout);
        reset.setLayoutX((gameArea.getWidth() / 2 - (reset.getWidth() / 2)));
        reset.setLayoutY(1);
            /*
            End of sprout background creation
             */
        reset.setOnAction(actionEvent -> {
            reset.setVisible(false);
            youLose.setVisible(false);
            Pause.setVisible(true);
            showInstructions.setVisible(true);
            logic.reset();
            logic.pause(false);
        });
       /*
       End of Reset Button Creation
        */

       /*
       Beginning of Pause creation
        */
        Pause.setLayoutX(gameArea.getWidth() - Pause.getWidth());
        Pause.setLayoutY(50);
        Pause.setVisible(true);
        Pause.setOnMouseClicked(mouseEvent -> pause(true, true));
        /*
        End of Pause Creation
         */


        reset.setVisible(false);
        youLose.setVisible(false);
        this.getChildren().add(reset);
        this.getChildren().add(youLose);
        this.getChildren().add(Pause);
        this.getChildren().add(showInstructions);
        this.getChildren().add(gameArea);

    }

    /**
     * Pause/Unpause the animation without touching the game timer
     * @param setAnimPause true to pause, false otherwise
     */
    public void pause(boolean setAnimPause) {
        if (setAnimPause) {
            animTimer.stop();
        } else {
            animTimer.start();
        }
    }

    /**
     * Pause/unpause the animation and game timer
     *
     * @param setAnimPause true to pause the animation timer
     * @param setGamePause true to pause the game timer
     */
    public void pause(boolean setAnimPause, boolean setGamePause ){
        this.pause(setAnimPause);
        logic.pause(setGamePause);
    }

    /**
     * Deal with key presses
     * @param event the event to handle
     */
    public void handleKeyPress(KeyEvent event){
        if( event.getCode() == KeyCode.A){
            logic.applyForce(GameLogic.DIRECTION.LEFT);
        }
        if( event.getCode() == KeyCode.D ) {
            logic.applyForce(GameLogic.DIRECTION.RIGHT);

        }
        if( event.getCode() == KeyCode.W ) {
            logic.applyForce(GameLogic.DIRECTION.UP);
        }
        if( event.getCode() == KeyCode.S ) {
            logic.applyForce(GameLogic.DIRECTION.DOWN);
        }

        if( event.getCode() == KeyCode.SPACE ){
            logic.applyForce(GameLogic.DIRECTION.STOP);
        }
        if( event.getCode() == KeyCode.SPACE ){
            logic.applyForce(GameLogic.DIRECTION.STOP);
        }
    }

    /**
     * Deal with key releases
     * @param event the event to handle
     */
    public void handleKeyRelease(KeyEvent event){
        if( event.getCode() == KeyCode.A){
            logic.removeForce(GameLogic.DIRECTION.LEFT);
        }
        if( event.getCode() == KeyCode.D ) {
            logic.removeForce(GameLogic.DIRECTION.RIGHT);
        }
        if( event.getCode() == KeyCode.W ) {
            logic.removeForce(GameLogic.DIRECTION.UP);
        }
        if( event.getCode() == KeyCode.S ) {
            logic.removeForce(GameLogic.DIRECTION.DOWN);
        }

        if( event.getCode() == KeyCode.SPACE ){
            logic.removeForce(GameLogic.DIRECTION.STOP);
        }
    }

    /**
     * Runs once per frame and handles all the drawing of each frame
     */
    private class AnimTimer extends AnimationTimer {


        @Override
        public void handle(long now) {
            GraphicsContext gc = gameArea.getGraphicsContext2D();
            youLose.setPrefSize(gameArea.getWidth(), gameArea.getHeight());
            gc.clearRect(0, 0, gameArea.getWidth(), gameArea.getHeight());
            Pause.autoSize(gameArea.getWidth(), 0.02);
            if (logic.isDisplayYouLose()) {
                Pause.setVisible(false);
                String space = """                        
                                               
                                             
                                               
                            
                            
                            
                            
                        """;
                youLose.setText(space + "Game Over, You Lose! \n" +
                        " You survived for " + logic.getSecondsAlive() + " seconds, \n" +
                        " and your total score was " + logic.getPlayerScore() + " points.\n" +
                        " Thank you for playing.\n" +
                        " We hope you had fun! ");
                youLose.autoSize(gameArea.getWidth());
                youLose.setVisible(true);
                youLose.toFront();
            }
            if (logic.isGameOver()) {
                Pause.setVisible(false);
                reset.autoSize(gameArea.getWidth());
                reset.setVisible(true);
                reset.toFront();

            } else {
                logic.render(gameArea);
            }
        }
    }
}
