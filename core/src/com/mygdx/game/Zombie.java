package com.mygdx.game;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Zombie extends Enemy {

    Animation<TextureRegion> huntAnimation;
    Texture zombieSheet;
    Rectangle enemy_hitbox;
    SpriteBatch batch;
    float prevX =0,prevY=0;
    boolean isDead = true;
    SoundEffects deathSound = new SoundEffects();

    public Zombie(SpriteBatch newBatch) {
        batch = newBatch;
        createAnimation();
        Random rand = new Random();
        // Initialising variables from superclass
        enemyY = randomiser.nextInt(maxY - minY) + minY;
        enemyX = randomiser.nextInt(maxX - minX) + minX;
        int random = rand.nextInt(8);
        speed = 100+random*3;
        width = 32-random*2;
        height = 32-random*2;
        hp = 50+random;
        facingRight = true;
        spawn = true;
        alive = true;
    }

    @Override
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
            if(isDead){
                deathSound.monsterDeath();
                isDead = false;
            }
        }
    }
    @Override
    public void drawHitbox(ShapeRenderer shapeRenderer){
        shapeRenderer.rect(enemyX, enemyY, width,
        height);
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
        enemy_hitbox = new Rectangle(enemyX, enemyY, width,
                height);
    }

    public void createAnimation() {
        zombieSheet = new Texture(Gdx.files.internal("monster.png"));

        TextureRegion[][] tmp = TextureRegion.split(zombieSheet,
                16,
                zombieSheet.getHeight());

        TextureRegion[] frames = new TextureRegion[3];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        huntAnimation = new Animation<TextureRegion>(0.33f, frames);
    }

    public void dispose() {
        zombieSheet.dispose();
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
