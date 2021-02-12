package org.headroyce.bsea;

import javafx.animation.AnimationTimer;
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

    public GameGUI() {
        gameArea = new Canvas();
        gameArea.heightProperty().bind(this.heightProperty());
        gameArea.widthProperty().bind(this.widthProperty());

        animTimer = new AnimTimer();
        logic = new GameLogic(gameArea.getWidth(), gameArea.getHeight());
        reset = new Button1("Reset");

        youLose = new Button1();
        Image skull = new Image("file:///Users/cole.henrich/Desktop/Skull&Crossbones.png");
        BackgroundImage skullImage = new BackgroundImage(skull, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
        Background skullBackground = new Background(skullImage);
        youLose.setBackground(skullBackground);
        reset.setMinSize(100, 100);
        Image image = new Image("file:///Users/cole.henrich/Desktop/Sprout.jpg");
//        BackgroundPosition(Side horizontalSide, double horizontalPosition, boolean horizontalAsPercentage, Side verticalSide, double verticalPosition, boolean verticalAsPercentage)
//        Creates a new BackgroundPosition.

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        reset.setBackground(background);
        reset.setOnAction(actionEvent -> {
            reset.setVisible(false);
            youLose.setVisible(false);
            logic.reset();
            logic.pause(false);
        });
        reset.setVisible(false);
        youLose.setVisible(false);
        this.getChildren().add(reset);
        this.getChildren().add(youLose);
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
     * Pause/unpause teh animation and game timer
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
            youLose.setSize(gameArea.getWidth() - 10, gameArea.getHeight() - 10, gameArea.getWidth(), gameArea.getHeight());
            gc.clearRect(0, 0, gameArea.getWidth(), gameArea.getHeight());
            if (logic.isDisplayYouLose()) {
                String txt = ("""                 
                                                                                                                                                                                                                                                                                                                
                        """);
                youLose.setText("\n" + txt + "\n You survived for: \n" +
                        "" + logic.getSecondsAlive() + " seconds,\n" +
                        " and your total score was " + logic.getPlayerScore() + " points. ");
                youLose.setVisible(true);
                youLose.toFront();
            }
            if (logic.isGameOver()) {
                reset.setVisible(true);
                reset.toFront();
            } else {
                logic.render(gameArea);
            }

        }
    }
}
