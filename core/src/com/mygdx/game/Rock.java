package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Rock extends Projectile {
    Texture rockSheet;
    Circle rock_hitbox;
    SpriteBatch batch;
    ShapeRenderer myRenderer;
    float targetX;
    float targetY;
    float gradient;
    boolean latchX;
    boolean latchY;

    public Rock(SpriteBatch newBatch, float enemyX, float enemyY, Player player) {
        batch = newBatch;
        // Initialising variables from superclass
        projectileX = enemyX; // This is where the rock will spawn - change so its not random.
        projectileY = enemyY;
        speed = 2;
        width = 10;
        height = 10;
        hp = 50;
        spawn = true;
        active = true;
        myRenderer = new ShapeRenderer();
        rock_hitbox = new Circle(projectileX, projectileY, 5);
        targetX = player.getPlayerX();
        targetY = player.getPlayerY();

    }

    @Override
    public void draw(float x, float y) {
        stateTime += Gdx.graphics.getDeltaTime(); // Used for animation

        Texture currentFrame = new Texture(Gdx.files.internal("rock.png"));
        TextureRegion rock = new TextureRegion(currentFrame, 10, 10);
        if (spawn) {
            batch.draw(rock, projectileX, projectileY, width, height);
            gradient = ((targetY - projectileY) / (targetX - projectileX));
            if (gradient < 0) {
                gradient *= -1;
            }
            if(projectileX > targetX){latchX = true;} else{latchX=false;}
            if(projectileY > targetY){latchY = true;} else{latchY=false;}
            spawn = false;
        }
        if (active) {
            hunt(rock, targetX, targetY);
        }
    }

    public void hunt(TextureRegion rock, float targetX, float targetY) {
        if (latchX) {
            projectileX -= (speed);
        }
        else {
            projectileX += (speed);
        }
        if (latchY) {
            projectileY -= (speed * gradient);
        }
        else {
            projectileY += (speed * gradient);
        }

        batch.draw(rock, projectileX, projectileY, width, height);

        rock_hitbox = new Circle(projectileX, projectileY, 5);

    }

    public float getProjectileX() {
        return projectileX;
    }

    public float getProjectileY() {
        return projectileY;
    }

    public void dispose() {
        rockSheet.dispose();
    }

    public Circle getRockHitbox() {
        return rock_hitbox;
    }

    public boolean getActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

}
