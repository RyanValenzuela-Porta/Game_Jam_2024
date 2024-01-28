package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float villainspeed = 0.05f;
	float playerx = 0;
	float playery = 0;
	float swordx = 0;
	float swordy = 0;
	float speed = 50f;
	float enemyx = 900, enemyY = 0;
	Rectangle player_rect;
	Rectangle enemy_rect, sword_rect;
	float prevX,prevY;
	Texture player;
	Texture sword;
	Texture enemy;
	Stage stage;


	@Override
	public void create () {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		prevX =0;
		prevY = 0;
		player = new Texture("test.jpg");
		sword = new Texture("sword.png");
		enemy = new Texture("villain.png");
		player_rect = new Rectangle(playerx,playery,player.getWidth(),player.getHeight());
		enemy_rect = new Rectangle(enemyx,enemyY,10,500);
		sword_rect =new Rectangle(swordx,swordy,sword.getWidth(),sword.getHeight());
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		if ( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP) ) {
			prevY = playery;
			playery += (Gdx.graphics.getDeltaTime() * speed);
			swordy += (Gdx.graphics.getDeltaTime() * speed);
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			prevX = playerx;
			playerx -= (Gdx.graphics.getDeltaTime() * speed);
			swordx -= (Gdx.graphics.getDeltaTime() * speed);
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.S) ||Gdx.input.isKeyPressed(Input.Keys.DOWN) ) {
			prevY = playery;
			playery -= (Gdx.graphics.getDeltaTime() * speed);
			swordy -= (Gdx.graphics.getDeltaTime() * speed);
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			prevX = playerx;
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
		if (enemy_rect.overlaps(player_rect)) {
			System.out.println(("collision"));
			playery = prevY;
			playerx = prevX;
		}
		if(sword_rect.overlaps(enemy_rect)) {
			System.out.println(("collision2"));
			playery = prevY;
			playerx = prevX;
		}
		batch.begin();
		stage.draw();
		batch.draw(player, playerx,playery);
		batch.draw(sword,swordx,swordy);
		batch.draw(enemy,enemyx,enemyY);
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1200, 650);
		player_rect = new Rectangle(playerx,playery,player.getWidth(),player.getHeight());
		enemy_rect = new Rectangle(enemyx,enemyY,10,500);


		enemyx += (playerx - enemyx) * villainspeed * Gdx.graphics.getDeltaTime();
		enemyY += (playery - enemyY) * villainspeed * Gdx.graphics.getDeltaTime();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
