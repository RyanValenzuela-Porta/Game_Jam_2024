package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sword sword;
	Player player;
	Map map;
	OrthographicCamera camera = new OrthographicCamera();
	Soundtrack music;
	int gameState=0; //gameState 0 means load the actual game, 1 means load the start screen, more states can be added later
	//Zombie zombie;
	int wave=1;
	boolean waveStarted=false;
	Enemies enemies;
	@Override
	public void create() {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		player = new Player(batch);
		music = new Soundtrack();
		sword = new Sword(batch);
		//zombie = new Zombie(batch);
		enemies= new Enemies(batch,player);
		//Note that all of this is set as soon as the game opens, may want to change this later for startup screen.
		music.load();
		camera.setToOrtho(false);

		// instiate map
		map = new Map();

	}

	@Override
	public void render() {
		switch(gameState){
			case 0:
				renderGameMap();
				break;
			case 1:
				renderStartScreen();
				break;
		}

	}
	public void renderStartScreen(){
		//Render the startup screen here
	}
	public void renderGameMap(){
				// handleInput();
				camera.position.set(player.getPlayerX(), player.getPlayerY(), 0); //Set the camera to where the player is
				camera.update(); //Refresh
				ScreenUtils.clear(42 / 255f, 45 / 255f, 60 / 255f, 1); //Set color of background
				
				// update map
				map.render(camera); //map render

				// ties the sword X and Y positions to the players x and y
				sword.setSwordX(player.getPlayerX());
				sword.setSwordY(player.getPlayerY());

				// the sword needs to know what direction the character is facing to control which sword image is shown
				sword.setDirectionFacing(player.isFacingRight());
				batch.begin(); 
		
				// All draw methods here
				player.draw();
				sword.draw();
				if(!waveStarted){
					enemies.spawnEnemies(wave);
					waveStarted=true;
				}
				//zombie.draw(player.getPlayerX(),player.getPlayerY());
				enemies.draw();
				batch.setProjectionMatrix(camera.combined);
		
				batch.end();
		
				// close game after pressing Esc button
				if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
					Gdx.app.exit();
				}
	}
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = 450f;
		camera.viewportHeight = 450f * height / width;
		camera.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
		player.dispose();
		map.dispose();
		//zombie.dispose();
	}
}
