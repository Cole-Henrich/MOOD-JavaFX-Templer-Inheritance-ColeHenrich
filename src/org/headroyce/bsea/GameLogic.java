package org.headroyce.bsea;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Represents the logic of our game
 */
public class GameLogic {

    public enum DIRECTION {
        LEFT,
        UP,
        RIGHT,
        DOWN,
        STOP,
        NONE,

    }

    // The game step in milliseconds
    public static final int GAME_STEP_TIMER = 17;
    private GameTimer gameTimer;

    private boolean gameOver;

    private Random rand;

    // The player
    private Ball player;
    private HashMap<DIRECTION, Boolean> forcesOnPlayer;

    private static final int PLAYER_FLASH_TIME = 500;
    private int flashTimer = 500;

    private static final int PLAYER_SCORING_TIME = 5000;
    private int PLAYER_SCORING_TIMER = 5000;

    private static final int PLAYER_SCORING_POINTS = 2;
    private int playerScore = 0;

    private static final int ENEMY_SPAWN_TIME = 150;
    private static final int ENEMY_DIRECTION_PROBABILITY = 5;
    private static final int ENEMY_SPAWN_PROBABILITY = 5;
    private static final int OBSTACLE_SPAWN_PROBABILITY = 10;
    private int ENEMY_SPAWN_TIMER = 150;

    ArrayList<String>allHexes = getAllHexes();
    // Enemy Elements
    private ArrayList<Mob> enemies;

    // Width and height of the canvas
    private double width, height;

    public GameLogic(double width, double height){
        rand = new Random();

        gameTimer = new GameTimer();

        this.width = Math.abs(width);
        this.height = Math.abs(height);

        player = new Ball();
        enemies = new ArrayList<>();

        forcesOnPlayer = new HashMap<>();

        reset();
    }

    /**
     * Renders the game elements onto a canvas
     * @param canvas the canvas to render onto
     */
    public void render(Canvas canvas){

        // Update width and height
        double width = canvas.getWidth();
        height = canvas.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        player.render(canvas);
        for (Mob enemy : enemies) {
            int min = (int) enemy.getWidth();
            if (enemy.x < 0) {
                int maxW = (int) (width - min + 1);
                enemy.x = rand.nextInt(maxW - min + 1) + min;
            }

            enemy.render(canvas);
        }

        // Draw lives and score last so that the balls go under them
        Text lives = new Text("Lives: " + Math.round(player.getHP()));

        gc.strokeText("Score: " + playerScore, 10, 30);
        gc.strokeText(lives.getText(),width - 10 - lives.getLayoutBounds().getWidth(), 20);
    }

    /**
     * Pause or unpause the game
     * @param setPaused true to pause, false otherwise
     */
    public void pause(boolean setPaused ){
        if( setPaused ){
            gameTimer.stop();
        }
        else {
            gameTimer.start();
        }
    }

    public void reset(){
        player.x = 200;
        player.y = 200;
        player.setRadius(10);

        player.velX = player.velY = 0;
        player.setVelocityBoundX(-7, 7);
        player.setVelocityBoundY(-7,7);


        player.addHP(3);
        enemies.clear();
        forcesOnPlayer.clear();

        gameOver = false;
        playerScore = 0;

        enemies.clear();
    }

    public boolean isGameOver(){
        return gameOver;
    }

    private boolean collideWalls(Mob player){

        boolean collided = false;

        // Keep player with the window

        if( player == this.player ) {
            if (player.y + player.getHeight() > height) {
                player.y = height - player.getHeight();
                player.bounceY();
                collided = true;
            }

            if (player.y < 0) {
                player.y = 0;
                player.bounceY();
                collided = true;
            }
        }


        if( player.x + player.getWidth() > width ){
            player.x = width - player.getWidth();
            player.bounceX();
            collided = true;
        }
        if( player.x < 0 ){
            player.x = 0;
            player.bounceX();
            collided = true;
        }

        return collided;
    }

    public void applyForce( DIRECTION direction ) {
        forcesOnPlayer.put(direction, true);
    }

    public void removeForce(DIRECTION direction){
        forcesOnPlayer.remove(direction);
    }

    /**
     * Runs once per game tick which is set dynamically by the GAME_STEP_TIMER
     */
    private class GameTimer extends AnimationTimer {
        // The last nanosecond
        private long lastUpdate;

        public GameTimer() {
            lastUpdate = 0;
        }

        @Override

        public void handle(long now) {

            // Covert the time_elapsed from nanoseconds to milliseconds
            long time_elapsed = (now - lastUpdate)/1000000;

            flashTimer -= time_elapsed;
            if( flashTimer < 0){
                player.setColor(Color.BLACK);
            }

            PLAYER_SCORING_TIMER -= time_elapsed;
            if( PLAYER_SCORING_TIMER < 0 ){
                PLAYER_SCORING_TIMER = PLAYER_SCORING_TIME;
                playerScore += PLAYER_SCORING_POINTS;
            }

            ENEMY_SPAWN_TIMER -= time_elapsed;
            if( ENEMY_SPAWN_TIMER < 0 ){
                int chance = rand.nextInt(100);
                if( chance < OBSTACLE_SPAWN_PROBABILITY ){

                    if( chance < ENEMY_SPAWN_PROBABILITY ) {
                        LifeGiver lifeGiver = new LifeGiver();
                        Ball unAmigo = new Ball(-1);
                        Ball[] either = {lifeGiver, unAmigo};
                        for (int i = 0; i < 2; i++) {
                            Ball enemy = either[i];
                            enemy.x = -1;
                            enemy.y = -enemy.getRadius();  // off screen
                            enemy.setVelocityBoundX(-5, 5);
                            enemy.setVelocityBoundY(0, 5);

                            enemy.velX = rand.nextInt(5) + 2;
                            enemy.velY = rand.nextInt(5)+5;
                            enemies.add(enemy);
                        }
                    }
                    else{
                        double spikeX = 0;
                        if (Math.random() < 0.5){
                            spikeX = -1;
                        }
                        if (Math.random() >= 0.5){
                            spikeX = width;
                        }
                        Obstacle obstacle = new Obstacle();
                        SpikedWall spikedWall = new SpikedWall(20, 200, spikeX , getRandomColor());
                        Obstacle[] rectangles = {obstacle, spikedWall};
                        for (Obstacle enemy: rectangles) {
                            enemy.y = -enemy.getHeight();  // off screen
                            enemy.setVelocityBoundX(-5,5);
                            enemy.setVelocityBoundY(0,10);
                            enemy.velY = 5;
                            enemies.add(enemy);
                        }
                    }
                }
                ENEMY_SPAWN_TIMER = ENEMY_SPAWN_TIME;
            }

            if( time_elapsed > GameLogic.GAME_STEP_TIMER) {
                // Game steps go here

                if( forcesOnPlayer.containsKey(DIRECTION.LEFT) ){
                    player.velX--;
                }
                if( forcesOnPlayer.containsKey(DIRECTION.RIGHT) ){
                    player.velX++;
                }
                if( forcesOnPlayer.containsKey(DIRECTION.UP) ){
                    player.velY--;
                }
                if( forcesOnPlayer.containsKey(DIRECTION.DOWN) ){
                    player.velY++;
                }

                if( forcesOnPlayer.containsKey(DIRECTION.STOP) ){
                    player.velX -= Math.signum(player.velX);
                    player.velY -= Math.signum(player.velY);
                }

                // MOVE EVERYTHING
                player.move();
                for (Mob enemy : enemies) {
                    if (enemy instanceof Ball) {
                        if (rand.nextInt(100) < ENEMY_DIRECTION_PROBABILITY &&
                                enemy.getColor() == Color.RED
                        ) {
                            double changeX = Math.signum(player.x - enemy.x);
                            double changeY = Math.signum(player.y - enemy.y);

                            enemy.velX = changeX * Math.abs(enemy.velX);
                            enemy.velY = changeY * Math.abs(enemy.velY);
                        }
                    }

                    enemy.move();
                }
                // CHECK WALLS ON EVERYTHING
                boolean playerCollided = collideWalls(player);
                for( int i = 0; i < enemies.size(); i++ ){
                    Mob enemy = enemies.get(i);
                    collideWalls(enemy);

                    if( enemy.y > height ){
                        enemies.remove(enemy);
                        i--;
                    }
                }

                // CHECK BALL COLLISIONS ON EVERYTHING
                double hpDamage = 0;
                for( int i = 0; i < enemies.size(); i++ ) {
                    Mob enemy = enemies.get(i);
                    for( int j = i + 1; j < enemies.size(); j++ ) {
                        if (enemy.intersects(enemies.get(j))) {
                            enemies.remove(j);
                            enemies.remove(enemy);
                            j -= 2;
                        }
                    }
                    boolean enemyRemove = enemy.intersects(player);
                    if( enemyRemove ){
                        hpDamage = enemy.getDamage();
                        player.velX = enemy.velX;
                        player.velY = enemy.velY;
                        enemies.remove(enemy);
                        i--;
                    }
                    playerCollided =  enemyRemove || playerCollided;

                }

                if( playerCollided ){
                    player.addHP(hpDamage);
                    // Stops lives being lost if green
                    if( flashTimer <= 0 ){
                        if( player.getHP() <= 0 ) {
                            gameOver = true;
                            pause(true);
                        }
                    }
                    flashTimer = PLAYER_FLASH_TIME;
                    player.setColor(getRandomColor());
                }
                lastUpdate = now;
            }
        }
    }

    /**
     * @return a random color
     */
    private Color getRandomColor(){
        int hexMax = (int) Math.pow(16, 6);
        int random = (int) (Math.random() *(hexMax));
        return Color.web(allHexes.get(random));
    }

    /**
     * @return all of the possible hexes
     */
    private ArrayList<String> getAllHexes() {
        char[] hexadecimal = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String hexCode = "";
        ArrayList<String>hexCodes = new ArrayList<>();
        for (char c2 : hexadecimal) {
            for (char c1 : hexadecimal) {
                for (char element : hexadecimal) {
                    for (char item : hexadecimal) {
                        for (char value : hexadecimal) {
                            for (char c : hexadecimal) {
                                hexCode = ("#" + c2 + "" + c1 + "" + element + "" + item + "" + value + "" + c + "");
                                hexCodes.add(hexCode);
                            }
                        }
                    }
                }
            }
        }
        return hexCodes;
    }
}
