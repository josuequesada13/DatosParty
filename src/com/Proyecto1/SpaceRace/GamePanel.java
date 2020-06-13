package com.Proyecto1.SpaceRace;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class GamePanel extends JPanel {

    /**
     * Class that creates a single panel where the game develops
     * @author Josue Quesada
     * @version 06/06/2020
     *
     */

    public int player;
    private Ship ship1;
    private Timer timer;
    public ArrayList<Asteroid> asteroidList;
    private Image background;
    public boolean win;

    /**
     * This method creates the panel where the ship will appear
     * and move
     * @player
     */
    public GamePanel(int player) throws IOException {
        super();
        this.player = player;
        start();
        startAsteroid();
    }

    /**
     * Sets the size for the window and creates the ship object
     * @throws IOException
     */
    public void start() throws IOException {
        this.asteroidList = new ArrayList<>();
        setPreferredSize(new Dimension(350, 350));
        this.setVisible(true);
        this.ship1 = new Ship();
        setFocusable(true);
        this.timer = new Timer(10, new GameLoop(this));
        this.timer.start();
        setBackground();
    }

    /**
     * Creates the asteroids, sets their directions and saves them in an array
     * @throws IOException
     */
    private void startAsteroid() throws IOException {
        for(int i=0; i<10; i++){
            int direction = ThreadLocalRandom.current().nextInt(0,2);
            Asteroid asteroid = new Asteroid(ThreadLocalRandom.current().nextInt(8,332),
                    ThreadLocalRandom.current().nextInt(8,245));
            if (direction == 1){
                asteroid.setMovingToRight(false);
            }else{
                asteroid.setMovingToRight(true);
            }
            this.asteroidList.add(asteroid);
        }
    }

    /**
     * Constantly checks if the ship got a point and updates its position
     */
    public void doLoop(){
        updateMovement();
        ship1.gotPoint();
        //checkWin();
        repaint();
    }

    /**
     * Updates the ship`s position
     */
    public void updateMovement(){
        this.ship1.move();
        for(Asteroid asteroid:this.asteroidList){
            asteroid.move();
            if(asteroid.crashShip(ship1)){
                ship1.respawn();
            }
        }
    }

    public void setBackground() throws IOException {
        this.background = ImageIO.read(new File("images/spaceRaceBG.png"));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        drawShip(g);
        drawAsteroids(g);
    }

    private void drawShip(Graphics g){
        g.drawImage(ship1.getImage(), ship1.getX(), ship1.getY(), this);
    }

    private void drawAsteroids(Graphics g) {
        for(Asteroid asteroid:this.asteroidList){
            if(asteroid.isVisible()){
                g.drawImage(asteroid.getImage(), asteroid.getX(), asteroid.getY(),this);
            }
        }
    }

    public void drawBackground(Graphics g){
        g.drawImage(this.background, 0, 0,null);
    }

    public void moveUp(){
        this.ship1.dy = -2;
    }

    public void moveDown(){
        this.ship1.dy = 2;
    }

    public void shipPause(){
        this.ship1.dy = 0;
    }

    public boolean checkWin(){
        if(ship1.checkWin()){
            setwin();
            return true;
        }
        return false;
    }

    public void stopShip(){
        this.ship1.stop();
    }

    public void setwin(){
        this.win = true;
    }
}

