package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;

public class Player {
	private SpriteBatch batch;
	private Texture img;
	private float playerX = 505;
	private float playerY = 327;
	private float speed = 500;
	private int hp;
	private float regen;
	private float dmg;

	public Player(SpriteBatch newBatch, Texture newImg) {
		batch = newBatch;
		img = newImg;
	}

	public void draw() {
		img = new Texture("tileset.png");
		batch.draw(img, playerX, playerY, 126, 237, 18, 18);
		// batch.draw(img, 0, 0,0,0,100,100);
		int maxY = 655;
		int minY = 55;
		int maxX = 1205;
		int minX = 50;
		System.out.println(playerY);
		if (Gdx.input.isKeyPressed(Input.Keys.W) && playerY < maxY) {
			playerY += (Gdx.graphics.getDeltaTime() * speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S) && playerY > minY) {
			playerY -= (Gdx.graphics.getDeltaTime() * speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A) && playerX > minX) {
			playerX -= (Gdx.graphics.getDeltaTime() * speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D) && playerX < maxX) {
			playerX += (Gdx.graphics.getDeltaTime() * speed);
		}
	}

	public void dispose() {
		img.dispose();
	}

	public float getPlayerX() {
		return playerX;
	}

	public float getPlayerY() {
		return playerY;
	}
}
