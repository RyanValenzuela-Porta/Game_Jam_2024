package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;

public class Player {

	// animation
	private static final int cols = 9, rows = 1;
	Animation<TextureRegion> walkAnimation;
	Animation<TextureRegion> standAnimation;
	Texture walkSheet;
	float stateTime;

	private SpriteBatch batch;
	private float playerX = 505;
	private float playerY = 327;
	private float speed = 200;
	private int hp;
	private float regen;
	private float dmg;
	private boolean facingRight = true;
	private float width = 16;
	private float height = 16;
	private Rectangle player_hitbox, sword_hitbox;
	private ShapeRenderer shaperender;

	public Player(SpriteBatch newBatch) {
		batch = newBatch;
		createIdleAnimation();
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
				speed *= 2;
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

	public float getPlayerX() {
		return playerX;
	}

	public float getPlayerY() {
		return playerY;
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

}
