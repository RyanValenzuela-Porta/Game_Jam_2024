package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;

public class Sword {
    // sword should be tied to player at all times
    Texture swordimg;
    float stateTime;
    private SpriteBatch batch;
    private Rectangle sword_hitbox;
    private float swordX = 505;
    private float swordY = 327;

    private float width = 30;
    private float height = 9;
    private boolean facingRight;
    private boolean swordswung;
    SoundEffects sound;

	private int swordswungTimer = 0;
	private int swordswungDuration=30; //how many frames the sword is active for
	private int swordswungCooldownDuration=10;
	private boolean swordswungCooldownTimerRunning = false;
	private int swordswungCooldownTimer = 0;

    private ShapeRenderer shapeRenderer;

    public Sword(SpriteBatch newBatch,ShapeRenderer newShapeRenderer) {
        batch = newBatch;
        sound = new SoundEffects();
        displaySword();
        shapeRenderer = newShapeRenderer;
    }

    public void update(float playerX, float playerY, boolean newFacingRight){
        facingRight = newFacingRight;
        swordX = facingRight? playerX+10:playerX-15;
        swordY = playerY-2;
        
    }
    public void drawHitbox(){
		

		shapeRenderer.rect(swordX, swordY, width,
		height);
	}
    public void displaySword() {
        if (facingRight) {
            swordimg = new Texture("swordRIGHT.png");
        } else {
            swordimg = new Texture("sword.png");
        }
        swordswung = false;
    }

    public void swingSword() {
        if (facingRight) {
            swordimg = new Texture("swordswungRIGHT.png");
        } else {
            swordimg = new Texture("swordswung.png");
        }
        swordswung = true;
    }

    public void draw() {
        sword_hitbox = new Rectangle(swordX, swordY,width,
                height);
        //shaperender = new ShapeRenderer();

        if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !swordswungCooldownTimerRunning) || swordswung ==true) {
            swingSword();
            sound.swordplay();
            if(swordswungTimer == swordswungDuration){
                swordswungCooldownTimerRunning = true;
                swordswung=false;
                swordswungTimer=0;
            }
            swordswungTimer++;
            
        } else {
            displaySword();
            sound.restart();
            if(swordswungCooldownTimer == swordswungCooldownDuration){
                swordswungCooldownTimerRunning=false;
                swordswungCooldownTimer=0;
            }
            if(swordswungCooldownTimerRunning){
                swordswungCooldownTimer++;
            }

        }
        
        if (facingRight) {
            batch.draw(swordimg, swordX , swordY ); // adjust x and y slightly so sword appears infront of player
        } else {
            batch.draw(swordimg, swordX , swordY ); // adjust x and y slightly so sword appears infront of player
        }

    }

    public void dispose() {
        swordimg.dispose();
        sound.dispose();
    }

    public float getSwordX() {
        return (swordX);
    }

    public float getSwordY() {
        return swordY;
    }

    public void setSwordX(float x) {
        swordX = x;
    }

    public void setSwordY(float y) {
        swordY = y;
    }

    public void setDirectionFacing(boolean bool) {
        facingRight = bool;
    }

    public Rectangle getHitbox() {
        return sword_hitbox;
    }

    public boolean isSwung() {
        return swordswung;
    }
}
