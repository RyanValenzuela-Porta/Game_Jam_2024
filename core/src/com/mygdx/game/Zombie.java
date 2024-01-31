package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie {
    
    private static final int cols = 8, rows = 1;
    Animation<TextureRegion> huntAnimation;
    Texture zombieSheet;
    float stateTime;

    SpriteBatch batch;
    float zombieX = 510;
    float zombieY = 350;
    float speed = 100;
    float hp;
    float dmg;
    boolean facingRight = true;
    float targetX;
    float targetY;
    boolean spawn = true;
    float width = 32;
    float height = 32;
    
    public Zombie(SpriteBatch newBatch){
        batch = newBatch;
        createAnimation();
    }

    public void draw(float targetX, float targetY){
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = huntAnimation.getKeyFrame(stateTime, true);
        if(spawn){
            batch.draw(currentFrame, zombieX, zombieY);
            spawn = false;
        }
        hunt(currentFrame, targetX, targetY);
        
    }

    public void hunt(TextureRegion currentFrame, float targetX, float targetY){
        if(zombieX > targetX){
            zombieX -= (Gdx.graphics.getDeltaTime() * speed);
            facingRight = false;
        }
        if(zombieX < targetX){
            zombieX += (Gdx.graphics.getDeltaTime() * speed);
            facingRight = true;
        }
        if(zombieY > targetY){
            zombieY -= (Gdx.graphics.getDeltaTime() * speed);
        }
        if(zombieY < targetY){
            zombieY += (Gdx.graphics.getDeltaTime() * speed);
        }
        batch.draw(currentFrame, !facingRight ? zombieX+width : zombieX,zombieY,!facingRight ? -width:width,height);
    }

    public void createAnimation(){
        zombieSheet = new Texture(Gdx.files.internal("monster.png"));

        TextureRegion[][] tmp = TextureRegion.split(zombieSheet,
                                        16,
                                        zombieSheet.getHeight()/rows);
        
        TextureRegion[] frames = new TextureRegion[3];
        int index = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<3;j++){
                frames[index++] = tmp[i][j];
            }
        }
        huntAnimation = new Animation<TextureRegion>(0.33f, frames);
        stateTime = 0f;
    }

    public void dispose(){
        zombieSheet.dispose();
    }
}
