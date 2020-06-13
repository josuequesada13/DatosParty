package com.Proyecto1.SpaceRace;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Ship extends Sprite{
    private boolean movement;
    private boolean win;
    private final int width = 38;
    private final int height = 50;
    public int points = 0;

    public Ship() throws IOException {
        start();
    }

    private void start() throws IOException {
        BufferedImage imageicon = ImageIO.read(new File("images/Ship1.png"));
        setImage(imageicon);
        movement = true;
        win = false;

        int X = 175 - 25;
        int Y = 300 - 19;

        setX(X);
        setY(Y);

    }

    @Override
    public void move() {
        if(movement){
            y += dy;
            if(y<10){
                y=10;
            }else if(y>= 265){
                y=265;
            }
        }else{
            this.y = 265;
        }
    }

    public void respawn(){
        this.setY(265);
    }

    public void gotPoint(){
        if(this.getY() == 10){
            this.points ++;
            System.out.println(this.points);
            this.respawn();
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }

    public boolean checkWin(){
        if(points == 3){
            return true;
        }
        return false;
    }

    public void stop(){
        movement = false;
        respawn();
    }
}
