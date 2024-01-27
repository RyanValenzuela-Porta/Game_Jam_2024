package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float playerx = 0;
	float playery = 0;
	float swordx = 0;
	float swordy = 0;
	float speed = 50f;
	Texture player;
	Texture sword;
	Stage stage;
	@Override
	public void create () {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		player = new Texture("test.jpg");
		sword = new Texture("sword.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		if ( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP) ) {
			playery += (Gdx.graphics.getDeltaTime() * speed);
			swordy += (Gdx.graphics.getDeltaTime() * speed);
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			playerx -= (Gdx.graphics.getDeltaTime() * speed);
			swordx -= (Gdx.graphics.getDeltaTime() * speed);
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.S) ||Gdx.input.isKeyPressed(Input.Keys.DOWN) ) {
			playery -= (Gdx.graphics.getDeltaTime() * speed);
			swordy -= (Gdx.graphics.getDeltaTime() * speed);
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			playerx += (Gdx.graphics.getDeltaTime() * speed);
			swordx += (Gdx.graphics.getDeltaTime() * speed);
			System.out.println("Right");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			sword = new Texture("swordswung2.gif");
		}

		if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			sword = new Texture("sword.png");
			player = new Texture("test.jpg");
		}

		batch.begin();
		stage.draw();
		batch.draw(player, playerx,playery);
		batch.draw(sword,swordx,swordy);
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1200, 650);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
