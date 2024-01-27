package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input;

public class Player {
	private float playerX;
	private float playerY;

	public Player(float playerX , float playerY) {
		this.playerX = playerX;
		this.playerY = playerY;
	}
	
	public void move(float speed) {

	}

}