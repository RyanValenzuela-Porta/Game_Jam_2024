
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Archer extends Enemy{
    private static final int rows = 1;
    Animation<TextureRegion> huntAnimation;
    Texture archerSheet;
    Rectangle enemy_hitbox;
    Rectangle projectile_hitbox;
    SpriteBatch batch;
    Rock rock;
    ShapeRenderer shapeRenderer;
    float prevX= 0,prevY = 0;

    public Archer(SpriteBatch newBatch, ShapeRenderer renderer) {
        shapeRenderer = new ShapeRenderer();
        batch = newBatch;
        createAnimation();
        // Initialising variables from superclass
        enemyY = randomiser.nextInt(maxY - minY) + minY;
        enemyX = randomiser.nextInt(maxX - minX) + minX;
        speed = 100;
        width = 25;
        height = 25;
        hp = 50;
        facingRight = true;
        spawn = true;
        alive = true;
        rock = new Rock(newBatch, enemyX, enemyY);

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
            rock.draw(targetX, targetY);
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
        enemy_hitbox = new Rectangle(!facingRight ? enemyX + width : enemyX, enemyY, !facingRight ? -width : width, height);
    }

    public void createAnimation() {
        archerSheet = new Texture(Gdx.files.internal("archer.png"));

        TextureRegion[][] tmp = TextureRegion.split(archerSheet,16, archerSheet.getHeight() / rows);
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

    public void drawHitbox(ShapeRenderer renderer){
        
		renderer.circle(enemyX+8, enemyY+8, 100);
    }

    public void dispose() {
        archerSheet.dispose();
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
