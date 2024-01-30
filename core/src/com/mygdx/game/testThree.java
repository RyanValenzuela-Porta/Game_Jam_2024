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

public class testThree extends ApplicationAdapter{
	
	
	OrthographicCamera camera;
	SpriteBatch batch;
	Sprite sprite;
	// audio
	private Music background;
	private float rotationSpeed;
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;

	@Override
	public void create () {

		rotationSpeed = 0.5f;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		//camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight /2f, 0);
		camera.setToOrtho(false);
		camera.update();
		batch = new SpriteBatch();
		//load background music
		background = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
		background.setLooping(true);
		background.play();

		tiledMap = new TmxMapLoader().load("map1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	}

	@Override
	public void render () {
		handleInput();
		camera.update();
		ScreenUtils.clear(42/255f, 45/255f, 60/255f, 1);
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.end();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
	}
	
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.translate(0, -3);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.translate(0, 3);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)){
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		}
		

		//camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100/camera.viewportWidth);

		//float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
		//float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

		//camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
		//camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = 450f;
		camera.viewportHeight = 450f * height/width;
		camera.update();
	}

	@Override
	public void dispose () {
		background.dispose();
		
	}
}
