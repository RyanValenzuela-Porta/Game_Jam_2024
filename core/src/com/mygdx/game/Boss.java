package com.mygdx.game;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
public class Boss extends Enemy {
    /**
     * To do:
     * Health bar
     * Sword
     * Upgrades 
     */
    Animation<TextureRegion> huntAnimation;
    Texture bossSheet;
    int maxhp=5000;
    Rectangle enemy_hitbox;
    SpriteBatch batch;
    SpriteBatch hudBatch;
    float prevX =0,prevY=0;
    ArrayList<int[]> bossUpgrades;
    SoundEffects sound;
    boolean soundflip = true;
    boolean isBossDead = false;
    public Boss(SpriteBatch newBatch, SpriteBatch newhudBatch) {
        batch = newBatch;
        hudBatch = newhudBatch;
        createAnimation();
        Random rand = new Random();
        sound = new SoundEffects();
        // Initialising variables from superclass
        enemyY = randomiser.nextInt(maxY - minY) + minY;
        enemyX = randomiser.nextInt(maxX - minX) + minX;
        int random = rand.nextInt(8);
        speed = 200;
        width = 32;
        height = 32;
        hp = 5000;
        facingRight = true;
        spawn = true;
        alive = true;
        dmg=2;
    }
    public void drawHealthBar(){
        TextureRegion blankHealth =new TextureRegion( new Texture(Gdx.files.internal("bossBar.png")),169,26); 
        TextureRegion redHealth=new TextureRegion( new Texture(Gdx.files.internal("bossBar2.png")),159,13);
        //in bossBar.png the width of the fillable area is 159px wide and 14px high, meaning the black border on the left and right sides is 5px and 6px on the top and bottom
        float blankHealthWidth=Gdx.graphics.getWidth()-100;
        float blankHealthHeight=30;
        float blankHealthX = Gdx.graphics.getWidth()/2 - blankHealthWidth/2;
        float blankHealthY = Gdx.graphics.getHeight()-blankHealthHeight-10;
        float healthBarScaleX = blankHealthWidth/169;
        float healthBarScaleY = blankHealthHeight/26;
        float redHealthWidth = 159* healthBarScaleX * (hp/maxhp);
        float redHealthHeight = 16* healthBarScaleY;
        float redHealthX = blankHealthX+(5*healthBarScaleX) ;
        float redHealthY = blankHealthY+(6*healthBarScaleY);
        //System.out.println(redHealthWidth);
        //System.out.println(redHealthHeight);
        hudBatch.draw(blankHealth,blankHealthX,blankHealthY,blankHealthWidth,blankHealthHeight);
        hudBatch.draw(redHealth,redHealthX,redHealthY,redHealthWidth,redHealthHeight);
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
            if(soundflip){
                sound.monsterDeath();
                soundflip = false;
            }
            batch.setColor(0.1f, 0.1f, 0.1f, 0.7f);
            batch.draw(currentFrame, enemyX, enemyY);
            batch.setColor(1, 1, 1, 1);
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
        bossSheet = new Texture(Gdx.files.internal("dinoBossWalksheet.png"));

        TextureRegion[][] tmp = TextureRegion.split(bossSheet,
                16,
                bossSheet.getHeight());

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
        bossSheet.dispose();
    }

    public Rectangle getHitbox() {
        return enemy_hitbox;
    }

    public boolean getAlive() {
        return alive;
    }

    public boolean goToCredits(){
        return true;
    }
    public boolean isBossDead(){
        if(hp <= 0){
            return true;
        }
        return false;
    }
    public float getPrevX(){ return prevX;}
    public float getPrevY(){return prevY;}

    public void setX(float x) { enemyX = x; }
    public void setY(float y) { enemyY = y; }

}
