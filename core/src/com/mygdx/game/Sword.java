package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input;

public class Sword {
	private float swordX;
	private float swordY;
	private float swingSpeed;
	private float swordSize;

	public Sword(float swordX , float swordY) {
		this.swordX = swordX;
		this.swordY = swordY;
	}

	public void move(float speed) {

	}
}