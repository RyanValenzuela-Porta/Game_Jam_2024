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
    int maxhp = 5000;
    Rectangle enemy_hitbox;
    SpriteBatch batch;
    SpriteBatch hudBatch;
    float prevX = 0, prevY = 0;
    ArrayList<int[]> bossUpgrades = new ArrayList<int[]>();
    SoundEffects sound;
    boolean soundflip = true;
    boolean isBossDead = false;
    ArrayList<Rock> rocksArray = new ArrayList<>();
    float rockCooldownDuration = 100;
    boolean rockCooldownActive = false;
    Player player;
    Rock rock;
    
    checkCollidable collisionDetector;

    public Boss(SpriteBatch newBatch, SpriteBatch newhudBatch, Player newPlayer) {
        batch = newBatch;
        hudBatch = newhudBatch;
        createAnimation();
        Random rand = new Random();
        sound = new SoundEffects();
        // Initialising variables from superclass
        enemyY = randomiser.nextInt(maxY - minY) + minY;
        enemyX = randomiser.nextInt(maxX - minX) + minX;
        int random = rand.nextInt(8);
        speed = 150;
        width = 32;
        height = 32;
        hp = 5000;
        facingRight = true;
        spawn = true;
        alive = true;
        player = newPlayer;
        collisionDetector = new checkCollidable(player, rock);
        dmg = 2;
        
        
    }

    public void drawHealthBar() {
        TextureRegion blankHealth = new TextureRegion(new Texture(Gdx.files.internal("bossBar.png")), 169, 26);
        TextureRegion redHealth = new TextureRegion(new Texture(Gdx.files.internal("bossBar2.png")), 159, 13);
        // in bossBar.png the width of the fillable area is 159px wide and 14px high,
        // meaning the black border on the left and right sides is 5px and 6px on the
        // top and bottom
        float blankHealthWidth = Gdx.graphics.getWidth() - 100;
        float blankHealthHeight = 30;
        float blankHealthX = Gdx.graphics.getWidth() / 2 - blankHealthWidth / 2;
        float blankHealthY = Gdx.graphics.getHeight() - blankHealthHeight - 10;
        float healthBarScaleX = blankHealthWidth / 169;
        float healthBarScaleY = blankHealthHeight / 26;
        float redHealthWidth = 159 * healthBarScaleX * (hp / maxhp);
        float redHealthHeight = 16 * healthBarScaleY;
        float redHealthX = blankHealthX + (5 * healthBarScaleX);
        float redHealthY = blankHealthY + (6 * healthBarScaleY);
        
        // System.out.println(redHealthWidth);
        // System.out.println(redHealthHeight);
        hudBatch.draw(blankHealth, blankHealthX, blankHealthY, blankHealthWidth, blankHealthHeight);
        hudBatch.draw(redHealth, redHealthX, redHealthY, redHealthWidth, redHealthHeight);

        float bossBuffWidth = blankHealthWidth/8;
        float bossBuffHeight = 200;
        System.out.println(bossUpgrades.toString());
        //hudBatch.draw(new TextureRegion(new Texture(Gdx.files.internal("+speed.png"))),blankHealthX+(2*0*bossBuffWidth),blankHealthY-bossBuffHeight,bossBuffWidth,bossBuffHeight);
        for (int i = 0; i < bossUpgrades.size(); i++) {
            switch (bossUpgrades.get(i)[0]) { //draw buffs
                case 0:
                    //boss.increaseSpeed(50);
                    hudBatch.draw(new TextureRegion(new Texture(Gdx.files.internal("+speed.png"))),blankHealthX+(2*i*bossBuffWidth),blankHealthY-bossBuffHeight,bossBuffWidth,bossBuffHeight);
                    break;
                case 1:
                    //boss.increaseHP(500);
                    hudBatch.draw(new TextureRegion(new Texture(Gdx.files.internal("+hp.png"))),blankHealthX+(2*i*bossBuffWidth),blankHealthY-bossBuffHeight,bossBuffWidth,bossBuffHeight);
                    break;
                case 2:
                    //boss.increaseDmg(1);
                    hudBatch.draw(new TextureRegion(new Texture(Gdx.files.internal("+dmg.png"))),blankHealthX+(2*i*bossBuffWidth),blankHealthY-bossBuffHeight,bossBuffWidth,bossBuffHeight);
                    break;
            }
            switch (bossUpgrades.get(i)[1]) { //draw negatives
                case 0:
                    //boss.increaseSpeed(-40);
                    hudBatch.draw(new TextureRegion(new Texture(Gdx.files.internal("-speed.png"))),blankHealthX+((2*i*bossBuffWidth)+1),blankHealthY-bossBuffHeight,bossBuffWidth,bossBuffHeight);
                    break;
                case 1:
                    //boss.increaseHP(-500);
                    hudBatch.draw(new TextureRegion(new Texture(Gdx.files.internal("-hp.png"))),blankHealthX+((2*i*bossBuffWidth)+1),blankHealthY-bossBuffHeight,bossBuffWidth,bossBuffHeight);
                    break;
                case 2:
                    // if (boss.getDmg() != 1) {
                    //     boss.increaseDmg(-1);
                    // }
                    hudBatch.draw(new TextureRegion(new Texture(Gdx.files.internal("-dmg.png"))),blankHealthX+((2*i*bossBuffWidth)+1),blankHealthY-bossBuffHeight,bossBuffWidth,bossBuffHeight);
                    break;
                case 3:
                    //boss.increaseHitbox(8);
                    hudBatch.draw(new TextureRegion(new Texture(Gdx.files.internal("+size.png"))),blankHealthX+((2*i*bossBuffWidth)+1),blankHealthY-bossBuffHeight,bossBuffWidth,bossBuffHeight);
                    break;
            }
        }
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

            if(rockCooldownActive == false){
                rocksArray.add(new Rock(batch, enemyX, enemyY, player));
                rockCooldownActive = true;
            }
            else{
                rockCooldownDuration --;
                if(rockCooldownDuration == 0){
                    rockCooldownActive = false;
                    rockCooldownDuration = 100;
                }
            }
            rocksArray.forEach(rock -> {if(collisionDetector.checkProjectilePlayerCollision(rock.getRockHitbox(), rock.getActive())){
                rock.deactivate();
            }});
            
            rocksArray.forEach(rock -> rock.draw(targetX, targetY));

            hunt(currentFrame, targetX, targetY);
        } else {

            // If dead, draw the enemy as tinted and freeze the enemy.
            if (soundflip) {
                sound.monsterDeath();
                soundflip = false;
            }
            batch.setColor(0.1f, 0.1f, 0.1f, 0.7f);
            batch.draw(currentFrame, enemyX, enemyY);
            batch.setColor(1, 1, 1, 1);
        }
    }

    @Override
    public void drawHitbox(ShapeRenderer shapeRenderer) {
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

    public boolean goToCredits() {
        return true;
    }

    public boolean isBossDead() {
        if (hp <= 0) {
            return true;
        }
        return false;
    }

    public void increaseSpeed(int x) {
        speed += x;
    }

    public void increaseHP(int x) {
        hp += x;
    }

    public void increaseDmg(int x) {
        dmg += x;
    }

    public void increaseHitbox(int x) {
        width += x;
        height += x;
    }

    public float getPrevX() {
        return prevX;
    }

    public float getPrevY() {
        return prevY;
    }

    public void setX(float x) {
        enemyX = x;
    }

    public void setY(float y) {
        enemyY = y;
    }
    public void addUpgrade(int[] upgrade){
        bossUpgrades.add(upgrade);
    }
}
