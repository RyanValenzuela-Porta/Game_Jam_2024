package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Rock extends Projectile{
    Texture rockSheet;
    Circle rock_hitbox;
    SpriteBatch batch;
    ShapeRenderer myRenderer;

    public Rock(SpriteBatch newBatch, float enemyX, float enemyY, Player player) {
        batch = newBatch;
        // Initialising variables from superclass
        projectileX = enemyX; //This is where the rock will spawn - change so its not random.
        projectileY = enemyY;
        speed = 150;
        width = 10;
        height = 10;
        hp = 50;
        spawn = true;
        active = true;
        myRenderer = new ShapeRenderer();
        rock_hitbox = new Circle(projectileX, projectileY, 5);
    }

    @Override
    public void draw(float targetX, float targetY) {
        stateTime += Gdx.graphics.getDeltaTime(); // Used for animation

        Texture currentFrame = new Texture(Gdx.files.internal("rock.png"));
        TextureRegion rock = new TextureRegion(currentFrame,10,10);
        if (spawn) {
            batch.draw(rock, projectileX, projectileY,width,height);
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
        
        batch.draw(rock, projectileX, projectileY,width,height);

        rock_hitbox = new Circle(projectileX, projectileY, 5);

    }

    public float getProjectileX(){
        return projectileX;
    }

    public float getProjectileY(){
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

    public void deactivate(){
        active = false;
    }
    
}
