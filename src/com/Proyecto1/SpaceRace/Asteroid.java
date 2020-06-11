package com.Proyecto1.SpaceRace;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Asteroid extends Sprite{

    private final int width = 10;
    private final int height = 10;
    private boolean visible = true;
    private boolean movingToRight;

    public Asteroid(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        start();
    }

    private void start() throws IOException {
        BufferedImage imageicon = ImageIO.read(new File("images/asteroid.png"));
        setImage(imageicon);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isMovingToRight() {
        return movingToRight;
    }

    public void setMovingToRight(boolean movingToRight) {
        this.movingToRight = movingToRight;
    }

    @Override
    public void move() {
        if(isMovingToRight()) {
            this.x++;
            if(disappeared()){
                respawn();
            }
        }else if(!isMovingToRight()){
            this.x--;
            if(disappeared()){
                respawn();
            }
        }

    }

    public boolean disappeared(){
        if(this.isMovingToRight() && this.x > 342){
            return true;
        }else if(!this.isMovingToRight() && this.x < 0){
            return true;
        }
        return false;
    }

    public void respawn(){
        this.y = ThreadLocalRandom.current().nextInt(2,245);
        int direction = ThreadLocalRandom.current().nextInt(0,2);
        if (direction == 1){
            setX(332);
            setMovingToRight(false);
        }else{
            setMovingToRight(true);
        }
    }

    public boolean crashShip(Ship ship){
        return ship.getBounds().intersects(this.getBounds());

    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}
