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
	Texture walkSheet;
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
	private float baseSpeed = 200;
	private float sprintSpeed = 400;
	private int hp = 6;
	private int maxhp = 3;
	private float regen;
	private float dmg = 20;
	private boolean facingRight = true;
	private float width = 16;
	private float height = 16;
	private Rectangle player_hitbox;
	private ShapeRenderer shaperender;
	int aliveCount;
	private AssetManager assetManager;
	private Sound sound;
	private ArrayList<Float> heartList = new ArrayList<Float>(); // array list that can store 0, 0.5, or 1 corresponding
																	// to whether heart is full

	public Player(SpriteBatch newBatch, SpriteBatch newHudBatch) {
		batch = newBatch;
		createIdleAnimation();
		assetManager = new AssetManager();
		assetManager.load("playerdeath.mp3", Sound.class);
		assetManager.finishLoading();
		sound = assetManager.get("playerdeath.mp3", Sound.class);
		hudBatch = newHudBatch;
	}

	public void drawHearts() {
		int heartSize = 50;
		hearts = new Texture(Gdx.files.internal("hearts.png"));
		fullHeart = new TextureRegion(hearts,0,0,13,12 );
		halfHeart = new TextureRegion(hearts,16,0,13,12 );
		halfHeart = new TextureRegion(hearts,32,0,13,12 );
		heartList.clear();
		//fill up the array list
		for(int i=0;i<maxhp/2;i++){
			int tempHp = hp;
			if(tempHp>=2){ //Health greater than or equal to 2
				heartList.add(1f);
				tempHp-=2;
			}else if(tempHp==1){ //Health is 1
				heartList.add(0.5f);
				tempHp-=1;
			}else{
				heartList.add(0f);
			}
		}

		//draw the array list of hearts to the screen
		for(int i=0;i<heartList.size();i++){
			if(heartList.get(i)==1){
				hudBatch.draw(fullHeart,10+(i*heartSize),10,heartSize,heartSize);
			}else if(heartList.get(i)==0.5){
				hudBatch.draw(halfHeart,10+(i*heartSize),10,heartSize,heartSize);
			}else{
				hudBatch.draw(emptyHeart,10+(i*heartSize),10,heartSize,heartSize);
			}
		}
		//System.out.println("draw hearts is called");
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
		int maxY = 655;
		int minY = 55;
		int maxX = 1205;
		int minX = 52;
		// No input being pressed
		// System.out.println(hp);
		if (hp < 0) {
			sound.play();
		}
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
				speed = sprintSpeed;
			} else {
				speed = baseSpeed;
			}
			batch.draw(currentFrame, !facingRight ? playerX + width : playerX, playerY, !facingRight ? -width : width,
					height);
		}
	}

	public void dispose() {
		walkSheet.dispose();
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

	// start of upgrade methods...

	public void changeHP(int newmax) {
		// this can be used to upgrade / downgrade hp depending on upgrade choice
		hp = newmax;
	}

	public void increaseSpeed(float newSpeed) {
		baseSpeed += newSpeed;
		sprintSpeed += 2 * newSpeed;
	}

	public float getPlayerX() {
		return playerX;
	}

	public int getHP() {
		return hp;
	}

	public float getDmg() {
		return dmg;
	}

	public void increaseDmg(float x) {
		dmg += x;
	}

	public void increaseHitbox(float x) {
		width += x;
		height += x;
	}

	public float getPlayerY() {
		return playerY;
	}

	public void increaseHP(int x) {
		hp += x;
		maxhp += x;
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
}
