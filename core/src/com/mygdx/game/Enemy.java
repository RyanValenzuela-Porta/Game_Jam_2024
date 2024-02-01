package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {
    //All subclasses of enemies must have these variables.
    SpriteBatch batch;
    float stateTime;
    float enemyX,enemyY,speed,hp,dmg,targetX,targetY,width,height;
    boolean facingRight,spawn,alive;

    public Enemy createEnemy(){
        Zombie test = new Zombie(batch);
        return test;
    }
    //All subclasses should have one draw method that calls batch.draw()
    public void draw(float targetX, float targetY){
        
    }

    //All subclasses must have a createAnimation method
    public void createAnimation(){

    }

    //Getters and setters are shared between all methods
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

}
