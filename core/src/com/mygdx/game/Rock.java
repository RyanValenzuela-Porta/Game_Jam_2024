package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Rock extends Projectile{

    private static final int rows = 1;
    Texture rockSheet;
    Rectangle rock_hitbox;
    SpriteBatch batch;

    public Rock(SpriteBatch newBatch, float enemyX, float enemyY) {
        batch = newBatch;
        // Initialising variables from superclass
        projectileX = enemyX; //This is where the rock will spawn - change so its not random.
        projectileY = enemyY;
        speed = 150;
        width = 32;
        height = 32;
        hp = 50;
        spawn = true;
        active = true;
    }

    @Override
    public void draw(float targetX, float targetY) {
        stateTime += Gdx.graphics.getDeltaTime(); // Used for animation

        Texture currentFrame = new Texture(Gdx.files.internal("rock.png"));
        TextureRegion rock = new TextureRegion(currentFrame,10,10);
        if (spawn) {
            batch.draw(rock, projectileX, projectileY,50,50);
            spawn = false;
        }
        if (active) {
            hunt(rock, targetX, targetY);
        }
    }

    public void hunt(TextureRegion rock, float targetX, float targetY) {
        if (projectileX > targetX) {
            projectileX -= (Gdx.graphics.getDeltaTime() * speed);
        }
        if (projectileX < targetX) {
            projectileX += (Gdx.graphics.getDeltaTime() * speed);
        }
        if (projectileY > targetY) {
            projectileY -= (Gdx.graphics.getDeltaTime() * speed);
        }
        if (projectileY < targetY) {
            projectileY += (Gdx.graphics.getDeltaTime() * speed);
        }
        
        batch.draw(rock, projectileX, projectileY,16,16);

    }

    public void dispose() {
        rockSheet.dispose();
    }

    public Rectangle getHitbox() {
        return rock_hitbox;
    }

    public boolean getActive() {
        return active;
    }

    
}
