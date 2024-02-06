package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;
import com.badlogic.gdx.math.Rectangle;
public class Enemy extends MyGdxGame{
    //All subclasses of enemies must have these variables.
    SpriteBatch batch;
    float stateTime;

    Rectangle enemy_hitbox;
    float enemyX,enemyY,speed,hp,targetX,targetY,width,height,prevX,prevY;
    int dmg;
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

    public float getEnemyY() {
        return enemyY;
    }
    public void setEnemyY(float enemyY) {
        this.enemyY = enemyY;
    }
    public float getEnemyX() {
        return enemyX;
    }
    public boolean getFacingRight(){
        return facingRight;
    }
    public void setEnemyX(float enemyX) {
        this.enemyX = enemyX;
    }
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

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
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
    public float getPrevX(){ return prevX;}
    public float getPrevY(){return prevY;}

    public void setX(float x) { enemyX = x; }
    public void setY(float y) { enemyY = y; }


}
