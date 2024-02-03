package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;
public class Player {
	/**
	 * List:
	 * display health
	 * take damage animation - iframes
	 * upgrades
	 * 
	 * 
	 */
	// animation
	private static final int cols = 9, rows = 1;
	Animation<TextureRegion> walkAnimation;
	Animation<TextureRegion> standAnimation;
	Animation<TextureRegion> hitAnimation;
	Animation<TextureRegion> standHitAnimation;
	int hitTimer;
	boolean isHit;
	Texture walkSheet;
	Texture hitSheet;
	Texture hearts;
	TextureRegion fullHeart;
	TextureRegion halfHeart;
	TextureRegion emptyHeart;
	float stateTime;
	private SpriteBatch hudBatch;
	private SpriteBatch batch;
	private float playerX = 505;
	private float playerY = 327;
	private float speed = 200;
	private int hp = 1000;
	private int maxhp = 6;
	private float regen;
	private float dmg;
	private boolean facingRight = true;
	private float width = 16;
	private float height = 16;
	private Rectangle player_hitbox;
	private ShapeRenderer shaperender;
	int aliveCount;
	private AssetManager assetManager;
	private Sound sound;
	private ArrayList<Float> heartList=new ArrayList<Float>(); //array list that can store 0, 0.5, or 1 corresponding to whether heart is full

	public Player(SpriteBatch newBatch,SpriteBatch newHudBatch) {
		batch = newBatch;
		createIdleAnimation();
		createHitAnimation();
		assetManager = new AssetManager();
		assetManager.load("playerdeath.mp3", Sound.class);
		assetManager.finishLoading();
		sound = assetManager.get("playerdeath.mp3", Sound.class);
		hudBatch = newHudBatch;
		heartList.add(1f);
		heartList.add(1f);
		heartList.add(1f);
	}
	public void drawHearts(){
		int heartSize = 50;
		hearts = new Texture(Gdx.files.internal("hearts.png"));
		fullHeart = new TextureRegion(hearts,0,0,13,12 );
		halfHeart = new TextureRegion(hearts,16,0,13,12 );
		halfHeart = new TextureRegion(hearts,32,0,13,12 );
		hudBatch.draw(fullHeart,10,10,heartSize,heartSize);
		//System.out.println("draw hearts is called");
	}

	public void control(TextureRegion currentFrame, TextureRegion currentFrame2){
		int maxY = 655;
		int minY = 55;
		int maxX = 1205;
		int minX = 52;

		if (!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)
				&& !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
			// If the player is not facing right, draw the player with negative width to
			// flip them.
			batch.draw(currentFrame2, !facingRight ? playerX + width : playerX, playerY, !facingRight ? -width : width,
					height);
		}
		// Input being pressed
		else {
			if (Gdx.input.isKeyPressed(Input.Keys.W) && playerY < maxY) {
				playerY += (Gdx.graphics.getDeltaTime() * speed);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.S) && playerY > minY) {
				playerY -= (Gdx.graphics.getDeltaTime() * speed);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.A) && playerX > minX) {
				playerX -= (Gdx.graphics.getDeltaTime() * speed);
				facingRight = false;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D) && playerX < maxX) {
				playerX += (Gdx.graphics.getDeltaTime() * speed);
				facingRight = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				speed = 400;
			}
			batch.draw(currentFrame, !facingRight ? playerX + width : playerX, playerY, !facingRight ? -width : width,
					height);
		}
	}

	public void draw() {

		player_hitbox = new Rectangle(!facingRight ? playerX + width : playerX, playerY, !facingRight ? -width : width,
				height);
		shaperender = new ShapeRenderer();
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		// If the player is not facing right, draw the player with negative width to
		// flip them.

		TextureRegion currentFrame2 = standAnimation.getKeyFrame(stateTime, true);
		
		// No input being pressed
		// System.out.println(hp);
		if (hp < 0) {
			sound.play();
		}
		control(currentFrame, currentFrame2);
	}

	public void dispose() {
		walkSheet.dispose();
		hitSheet.dispose();
	}

	public void createIdleAnimation() {
		walkSheet = new Texture(Gdx.files.internal("dinoWalksheet.png"));

		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / cols,
				walkSheet.getHeight() / rows);

		TextureRegion[] walkFrames = new TextureRegion[cols * rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		TextureRegion[] standFrames = new TextureRegion[2];
		standFrames[0] = tmp[0][0];
		standFrames[1] = tmp[0][1];

		walkAnimation = new Animation<TextureRegion>(0.11f, walkFrames);
		standAnimation = new Animation<TextureRegion>(0.5f, standFrames);
		stateTime = 0f;
	}

	public void createHitAnimation(){
		hitSheet = new Texture("dinohit.png");

		TextureRegion[][] tmp = TextureRegion.split(hitSheet, hitSheet.getWidth() / cols, hitSheet.getHeight() / rows);
		TextureRegion[] hitFrames = new TextureRegion[cols * rows];
		TextureRegion[] standHitFrames = new TextureRegion[2];
		for(int i=0;i<rows;i++){
			hitFrames[i] = tmp[0][i];
			i++;
		}
		standHitFrames[0] = tmp[0][0];
		standHitFrames[1] = tmp[0][1];
		hitAnimation = new Animation<TextureRegion>(0.11f, hitFrames);
		standHitAnimation = new Animation<TextureRegion>(0.5f, standHitFrames);
		stateTime = 0f;
	}
	// start of upgrade methods...

	public void changeHP(int newmax) {
		// this can be used to upgrade / downgrade hp depending on upgrade choice
		hp = newmax;
	}

	public void setSpeed(float newspeed) {
		speed = newspeed;
	}

	public float getPlayerX() {
		return playerX;
	}

	public int getHP() {
		return hp;
	}

	public float getPlayerY() {
		return playerY;
	}

	public void setHP(int x) {
		hp = x;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setPlayerX(int xCoord) {
		playerX = xCoord;
	}

	public void setPlayerY(int yCoord) {
		playerY = yCoord;
	}

	public Rectangle getHitbox() {
		return player_hitbox;
	}
	
	public void setState(boolean value){
		isHit = value;
	}

	public void drawHit(){
		if(hitTimer == 30){
			setState(false);
		}
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = hitAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentFrame2 = standHitAnimation.getKeyFrame(stateTime, true);
		control(currentFrame, currentFrame2);
		
		hitTimer++;
	}
}
