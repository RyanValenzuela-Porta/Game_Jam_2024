package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class BossSword extends Enemy{
    Boss boss;
    Texture swordimg;
    float stateTime;
    private SpriteBatch batch;
    private float width = 21;
    private float height = 9;
    private boolean facingRight;
    //private Rectangle enemy_hitbox;
    SoundEffects sound;
    public BossSword(SpriteBatch newBatch,Boss newBoss){
        boss= newBoss;
        enemyX = boss.getEnemyX();
        enemyY = boss.getEnemyY();
        sound = new SoundEffects();
        displaySword();
        batch=newBatch;
        enemy_hitbox = new Rectangle(enemyX, enemyY,width,
        height);
        spawn = true;
        alive = true;
    }  
    public void displaySword() {
        if (facingRight) {
            swordimg = new Texture("swordRIGHT.png");
        } else {
            swordimg = new Texture("sword.png");
        }
    }

    @Override
    public void drawHitbox(ShapeRenderer shapeRenderer){
        shapeRenderer.rect(enemyX, enemyY, width,
        height);
    }
    @Override
     public void draw(float playerX, float playery) {
        System.out.println("boss sword draw called");
        enemy_hitbox = new Rectangle(enemyX, enemyY,width,
                height);
        facingRight = boss.getFacingRight();
        enemyX = boss.getEnemyX()+(facingRight?20:-15);
        enemyY = boss.getEnemyY();
        //shaperender = new ShapeRenderer();

        displaySword();
        sound.restart();
        batch.draw(swordimg, enemyX, enemyY );
    }
    public Rectangle getHitbox() {
        return enemy_hitbox;
    }
    public void dispose() {
        swordimg.dispose();
        sound.dispose();
    }
}
