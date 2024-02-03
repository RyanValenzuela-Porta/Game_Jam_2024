package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;
import com.badlogic.gdx.math.Rectangle;
public class Enemy {
    //All subclasses of enemies must have these variables.
    SpriteBatch batch;
    float stateTime;

    Rectangle enemy_hitbox;
    float enemyX,enemyY,speed,hp,dmg,targetX,targetY,width,height;
    boolean facingRight,spawn,alive;
    int maxY = 655;
    int minY = 55;
    int maxX = 1205;
    int minX = 50;
    Random randomiser = new Random();
    public Enemy createEnemy(){
        Zombie test = new Zombie(batch);
        return test;
    }
    //All subclasses should have one draw method that calls batch.draw()
    public void draw(float targetX, float targetY){}
    public void drawHitbox(ShapeRenderer shapeRenderer){};
    //All subclasses must have a createAnimation method
    public void createAnimation(){}

    //Getters and setters are shared between all methods
    public float getWidth() {
        return width;
    }
    public boolean getAlive() {
        return alive;
    }
    public void setAlive(boolean x) { this.alive = x; }

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

    public float getDmg() {
        return dmg;
    }

    public void setDmg(float dmg) {
        this.dmg = dmg;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Rectangle getHitbox() {
        return enemy_hitbox;
    }

}
