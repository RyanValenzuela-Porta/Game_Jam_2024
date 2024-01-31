package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.InputProcessor;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player player;
	Board board;
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	OrthographicCamera camera = new OrthographicCamera();
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(batch,img);
		board = new Board(batch,img);

		camera.setToOrtho(false);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight /2f, 0);

		tiledMap =  new TmxMapLoader().load("map.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
	}

	@Override
	public void render () {
		//handleInput();
		camera.update();
		ScreenUtils.clear(42/255f, 45/255f, 60/255f, 1);
		batch.begin();

		//All draw methods here
		// player.draw();
		// board.draw();

		batch.setProjectionMatrix(camera.combined);

		batch.end();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
	}
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = 450f;
		camera.viewportHeight = 450f * height/width;
		camera.update();
	}
	@Override
	public void dispose () {
		batch.dispose();
		//background.dispose();
		//img.dispose();
	}
}
