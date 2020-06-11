package com.Proyecto1.SpaceRace;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel {
    public int player;
    private Ship ship1;
    private Timer timer;
    public ArrayList<Asteroid> asteroidList;
    private Image background;
    public boolean win;

    public int x;
    public int y;



    public GamePanel(int player) throws IOException {
        super();
        this.player = player;
        start();
        startAsteroid();
    }

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

    public void doLoop(){
        updateMovement();
        ship1.gotPoint();
        checkWin();
        repaint();
    }

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

    public void shipStop(){
        this.ship1.dy = 0;
    }

    public void checkWin(){
        ship1.checkWin();
        if(ship1.getWin()){
            ship1.setMovement(false);
            win = true;
        }
    }

    public int getPlayer(){
        return player;
    }
}

