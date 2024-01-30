package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input;

public class Player {
	private float playerX;
	private float playerY;

	private SpriteBatch batch;
	private Texture player;

	public Player(float playerX , float playerY, SpriteBatch batch, Texture player) {
		this.playerX = playerX;
		this.playerY = playerY;
		this.batch = batch;
		this.player = player;
	}
	
	public void moveLeft(float speed) {
		playerX -= (Gdx.graphics.getDeltaTime() * speed);
	}

	public void moveRight(float speed) {
		playerX += (Gdx.graphics.getDeltaTime() * speed);
	}

	public void jump() {

	}


}