package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sword sword;
	Player player;
	Map map;
	Upgrades upgrades;
	SpriteBatch deathScreen;
	OrthographicCamera camera = new OrthographicCamera();
	Soundtrack music;
	int gameState = 1; // gameState 0 means load the actual game, 1 means load the start screen, more
						// states can be added later
	// Zombie zombie;
	int wave = 0;
	boolean waveStarted = false;
	Enemies enemies;
	SpriteBatch screen;
	Texture start1;
	Texture start2;
	private Texture dead;
	Menu menu;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		player = new Player(batch);
		music = new Soundtrack();
		sword = new Sword(batch);
		upgrades = new Upgrades(batch, player);
		// zombie = new Zombie(batch);
		enemies = new Enemies(batch, player, sword);
		// Note that all of this is set as soon as the game opens, may want to change
		// this later for startup screen.
		music.load();
		camera.setToOrtho(false);

		// instiate map
		map = new Map();

		screen = new SpriteBatch();
		menu = new Menu(screen);

		deathScreen = new SpriteBatch();
		start1 = new Texture("menu1.png");
		start2 = new Texture("menu2.png");
		dead = new Texture("death.png");
	}

	@Override
	public void render() {
		switch (gameState) {
			case 0:
				renderGameMap();
				break;
			case 1:
				screen.begin();
				menu.render();
				screen.end();
				if(menu.check()){
					gameState = 0;
				}
				break;
			case 2: // player dies
				renderDeathScreen();
		}

	}

	public void upgradeSelect() {
		upgrades.draw();
		// move player back to start so buttons are in correct place
		player.setPlayerX(505);
		player.setPlayerY(327);

		if (Gdx.input.getX() > 400 && Gdx.input.getX() < 480
				&& Gdx.input.getY() > 170 && Gdx.input.getY() < 230) {
			upgrades.leftHovered();
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				wave++;
				waveStarted = false;
			}
		} else if (Gdx.input.getX() > 870 && Gdx.input.getX() < 950
				&& Gdx.input.getY() > 170 && Gdx.input.getY() < 230) {
			upgrades.rightHovered();
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
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
		// handleInput();
		camera.position.set(player.getPlayerX(), player.getPlayerY(), 0); // Set the camera to where the player is
		camera.update(); // Refresh
		ScreenUtils.clear(42 / 255f, 45 / 255f, 60 / 255f, 1); // Set color of background

		// update map
		map.render(camera); // map render

		// ties the sword X and Y positions to the players x and y
		sword.setSwordX(player.getPlayerX());
		sword.setSwordY(player.getPlayerY());

		// the sword needs to know what direction the character is facing to control
		// which sword image is shown
		sword.setDirectionFacing(player.isFacingRight());
		batch.begin();

		// All draw methods here
		player.draw();
		sword.draw();
		if (!waveStarted) {
			enemies.spawnEnemies(wave);
			waveStarted = true;
		}

		if (enemies.checkEndOfWave()) {
			upgradeSelect();
		}

		enemies.draw();
		batch.setProjectionMatrix(camera.combined);

		batch.end();

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

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			player = new Player(batch);
			sword = new Sword(batch);
			enemies = new Enemies(batch, player, sword);
			waveStarted = false;
			gameState = 1;
		}
		deathScreen.begin();
		deathScreen.draw(dead, camera.viewportWidth, camera.viewportHeight);
		deathScreen.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
		player.dispose();
		map.dispose();
		sword.dispose();

		// zombie.dispose();
	}
}
