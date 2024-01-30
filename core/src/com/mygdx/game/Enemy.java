package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
public class Enemy {
    private float enemyX;
    private float enemyY;

    private float enemySpeed;

    private SpriteBatch batch;

    private Texture enemy;

    public Enemy(float enemyX,float enemyY,float enemySpeed,SpriteBatch batch,Texture enemy) {
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        this.enemySpeed = enemySpeed;
        this.batch = batch;
        this.enemy = enemy;
    }


    public float getX() {
        return enemyX;
    }

    public void setX(float newX) {
        enemyX = newX;
    }

    public float getY() {
        return enemyY;
    }

    public void setY(float newY) {
        enemyY = newY;
    }


}
