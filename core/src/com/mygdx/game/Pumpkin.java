
package com.mygdx.game;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Pumpkin extends Enemy{
    private static final int rows = 1;
    Animation<TextureRegion> huntAnimation;
    Texture pumpkinSheet;
    Rectangle enemy_hitbox;
    SpriteBatch batch;
    float prevX=0, prevY=0;

    public Pumpkin(SpriteBatch newBatch) {
        batch = newBatch;
        createAnimation();
        // Initialising variables from superclass
        Random rand = new Random();
        int random = rand.nextInt(8);
        enemyY = randomiser.nextInt(maxY - minY) + minY;
        enemyX = randomiser.nextInt(maxX - minX) + minX;
        speed = 100+random*3;
        width = 32-random*2;
        height = 32-random*2;
        hp = 50+random;
        facingRight = true;
        spawn = true;
        alive = true;
        dmg=1;
    }

    public void draw(float targetX, float targetY) {
        stateTime += Gdx.graphics.getDeltaTime(); // Used for animation

        TextureRegion currentFrame = huntAnimation.getKeyFrame(stateTime, false);

        if (spawn) {
            batch.draw(currentFrame, enemyX, enemyY);
            spawn = false;
        }
        if (alive) {
            currentFrame = huntAnimation.getKeyFrame(stateTime, true);
            hunt(currentFrame, targetX, targetY);
        } else {

            // If dead, draw the enemy as tinted and freeze the enemy.
            batch.setColor(0.1f, 0.1f, 0.1f, 0.7f);
            batch.draw(currentFrame, enemyX, enemyY);
            batch.setColor(1, 1, 1, 1);
        }
    }

    public void hunt(TextureRegion currentFrame, float targetX, float targetY) {
        prevX = enemyX;
        prevY = enemyY;
        if (enemyX > targetX) {
            enemyX -= (Gdx.graphics.getDeltaTime() * speed);
            facingRight = false;
        }
        if (enemyX < targetX) {
            enemyX += (Gdx.graphics.getDeltaTime() * speed);
            facingRight = true;
        }
        if (enemyY > targetY) {
            enemyY -= (Gdx.graphics.getDeltaTime() * speed);
        }
        if (enemyY < targetY) {
            enemyY += (Gdx.graphics.getDeltaTime() * speed);
        }

        batch.draw(currentFrame, !facingRight ? enemyX + width : enemyX, enemyY, !facingRight ? -width : width, height);
        enemy_hitbox = new Rectangle(enemyX, enemyY, width, height);
    }

    public void createAnimation() {
        pumpkinSheet = new Texture(Gdx.files.internal("pumpkin.png"));

        TextureRegion[][] tmp = TextureRegion.split(pumpkinSheet,16, pumpkinSheet.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[3];

        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 3; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        huntAnimation = new Animation<TextureRegion>(0.33f, frames);
        stateTime = 0f;
    }
    @Override
    public void drawHitbox(ShapeRenderer shapeRenderer){
        shapeRenderer.rect(enemyX, enemyY, width,
        height);
    }
    public void dispose() {
        pumpkinSheet.dispose();
    }

    public Rectangle getHitbox() {
        return enemy_hitbox;
    }

    public boolean getAlive() {
        return alive;
    }

    public float getPrevX(){ return prevX;}
    public float getPrevY(){return prevY;}

    public void setX(float x) { enemyX = x; }
    public void setY(float y) { enemyY = y; }
}
