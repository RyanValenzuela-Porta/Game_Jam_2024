package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;
import java.awt.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainGame implements Screen{
    SpriteBatch batch;
	OrthographicCamera camera;
    Texture img;
    float villainspeed = 0.05f;
    float playerx = 0;
    float playery = 0;
    float swordx = 0;
    float swordy = 0;
    float speed = 50f;
    float enemyx = 900, enemyY = 0;

    float enemyHP = 200;
    Rectangle enemy_rect, sword_rect;
    float prevX,prevY;
    Texture player;
    Texture sword;
    Texture enemy;
    Stage stage;

    ShapeRenderer shaperender;
    @Override
    public void show() {
        batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		camera = new OrthographicCamera(playerx,playery);
		camera.setToOrtho(false);
		prevX =0;
		prevY = 0;
		player = new Texture("test.jpg");
		sword = new Texture("sword.png");
		enemy = new Texture("villain.png");

		enemy_rect = new Rectangle(enemyx,enemyY,10,500);
		sword_rect =new Rectangle(swordx,swordy,sword.getWidth(),sword.getHeight());
		shaperender = new ShapeRenderer();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void render(float x) {
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
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			sword = new Texture("swordswung2.gif");
			if(sword_rect.overlaps(enemy_rect)) {
				System.out.println(("HIT"));
				enemyHP -= 20;
				playery = prevY;
				playerx = prevX;
			}
		}

		if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			sword = new Texture("sword.png");
			player = new Texture("test.jpg");
		}


		batch.begin();
		camera.position.set(playerx,playery,0);
		System.out.println(playerx);
		System.out.println(playery);
		System.out.println(camera.combined);
		camera.update();
		stage.draw();


		batch.draw(player, playerx,playery);
		batch.draw(sword,swordx,swordy);
      	if (enemyHP > 0) {
			batch.draw(enemy,enemyx,enemyY);
		}
		else {
			enemy.dispose();
		}
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1200, 650);

		enemy_rect = new Rectangle(enemyx+173,enemyY,enemy.getWidth()-340,enemy.getHeight()-20);
		sword_rect = new Rectangle(swordx,swordy,sword.getWidth(),sword.getHeight());

    	enemyx += (playerx - enemyx) * villainspeed * Gdx.graphics.getDeltaTime();
		enemyY += (playery - enemyY) * villainspeed * Gdx.graphics.getDeltaTime();
		batch.setProjectionMatrix(camera.combined);
		System.out.println("Camera Position: "+ camera.combined);
		batch.end();
//		shaperender.begin(ShapeRenderer.ShapeType.Line);
//		shaperender.setColor(Color.BLACK);
//		shaperender.rect(enemy_rect.x +173, enemy_rect.y,enemy_rect.width -340,enemy_rect.height -20);
//		shaperender.rect(sword_rect.x, sword_rect.y,sword_rect.width,sword_rect.height);
//		shaperender.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
		img.dispose();
    }
}
