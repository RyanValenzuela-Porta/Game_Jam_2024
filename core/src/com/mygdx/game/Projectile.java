package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {
    //All subclasses of enemies must have these variables.
    SpriteBatch batch;
    float stateTime;

    float speed,targetX,targetY,width,height, projectileX, projectileY, hp;
    boolean spawn, active;
    Rectangle projectile_hitbox;

    int maxY = 655;
    int minY = 55;
    int maxX = 1205;
    int minX = 50;
    Random randomiser = new Random();

    public void draw(float targetX, float targetY){}

    public void createAnimation(){}

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getTargetX() {
        return targetX;
    }

    public void setTargetX(float targetX) {
        this.targetX = targetX;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Rectangle getHitbox() {
        return projectile_hitbox;
    }

}
