package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	SpriteBatch hudBatch;
	OrthographicCamera camera;
	Map map;
	SpriteBatch screen;
	Texture dead;
	SpriteBatch deathScreen;
	Menu menu;
	Texture upgradeText;

	Sword sword;
	Player player;
	Upgrades upgrades;
	Soundtrack music;
	int gameState; // gameState 0 means load the actual game, 1 means load the start screen, more
					// states can be added later
	int wave;
	boolean waveStarted;
	Enemies enemies;
	checkCollidable collideCheck;

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		hudBatch = new SpriteBatch();
		player = new Player(batch, hudBatch, shapeRenderer);
		music = new Soundtrack();
		music.load();
		shapeRenderer = new ShapeRenderer();

		// instantiate map
		map = new Map();

		// starting screen
		screen = new SpriteBatch();
		menu = new Menu(screen);

		// death screen
		deathScreen = new SpriteBatch();
		dead = new Texture("endscreen.png");

		newCreate();
	}

	public void newCreate() {
		gameState = 1;
		player = new Player(batch, hudBatch, shapeRenderer);
		sword = new Sword(batch, shapeRenderer);
		upgrades = new Upgrades(batch, player);
		enemies = new Enemies(batch, player, sword, shapeRenderer);
		collideCheck = new checkCollidable(player, enemies, sword);
		waveStarted = false;
		gameState = 1;
		wave = 0;
	}

	@Override
	public void render() {
		switch (gameState) {
			case 0: // main game
				renderGameMap();
				break;
			case 1: // start screen
				screen.begin();
				menu.render();
				screen.end();
				if (menu.check()) {
					gameState = 0;
				}
				break;
			case 2: // end screen
				renderDeathScreen();
		}
		// close game after pressing Esc button
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

	}

	public void renderHUD() {
		hudBatch.begin();
		player.drawHearts();
		hudBatch.end();
	}

	public void renderStartScreen() {
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			gameState = 0;
		}
	}

	public void upgradeSelect() {
		upgrades.generateUpgradeLeft();
		upgrades.generateUpgradeRight();
		upgrades.draw();

		// move player back to start so buttons are in correct place
		player.setPlayerX(505);
		player.setPlayerY(327);

		if (Gdx.input.getX() > 400 && Gdx.input.getX() < 480
				&& Gdx.input.getY() > 170 && Gdx.input.getY() < 230) {
			upgrades.leftHovered();
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				upgrades.applyUpgradeL();
				wave++;
				waveStarted = false;
			}
		} else if (Gdx.input.getX() > 870 && Gdx.input.getX() < 950
				&& Gdx.input.getY() > 170 && Gdx.input.getY() < 230) {
			upgrades.rightHovered();
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				upgrades.applyUpgradeR();
				wave++;
				waveStarted = false;
			}
		} else {
			upgrades.leftNotHovered();
			upgrades.rightNotHovered();
		}
		upgrades.draw();
	}

	public void renderGameMap() {
		// set background color and update map
		ScreenUtils.clear(42 / 255f, 45 / 255f, 60 / 255f, 1);
		map.render(camera);

		// focus camera to player
		camera.position.set(player.getPlayerX(), player.getPlayerY(), 0);
		camera.update();

		// stick sword to player
		sword.update(player.getPlayerX(), player.getPlayerY(), player.isFacingRight());

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		// All draw methods here
		player.draw();
		sword.draw();
		if (!waveStarted) {
			enemies.spawnEnemies(wave);
			waveStarted = true;
		}

		if (enemies.checkEndOfWave()) {
			player.resetHp();
			upgradeSelect();
		}
		enemies.draw();
		batch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 1, 0, 1);
		enemies.drawHitbox(shapeRenderer);
		shapeRenderer.end();

		renderHUD();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 1, 0, 1);
		player.drawHitbox();
		sword.drawHitbox();
		enemies.drawHitboxes(shapeRenderer);
		shapeRenderer.end();

		// close game after pressing Esc button
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		if (player.getHP() < 0) {
			gameState = 2;
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = 450f;
		camera.viewportHeight = 450f * height / width;
		camera.update();

	}

	public void renderDeathScreen() {

		deathScreen.begin();
		deathScreen.draw(dead, 0, 0);
		deathScreen.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			newCreate();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
		player.dispose();
		map.dispose();
		sword.dispose();
		hudBatch.dispose();
		deathScreen.dispose();
		shapeRenderer.dispose();
		dead.dispose();
		screen.dispose();
		map.dispose();
		hudBatch.dispose();
	}
}
