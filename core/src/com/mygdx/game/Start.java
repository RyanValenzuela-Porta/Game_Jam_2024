package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
public class Start implements Screen{
    private SpriteBatch batch;
    private Texture start;
    @Override
    public void render(float h) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainGame());
        }

        batch.begin();
        batch.draw(start,start.getHeight(),start.getWidth());
        batch.end();
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        start = new Texture("startscreentest.jpeg");
    }

    @Override
    public void resize(int x, int y) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {

    }
    @Override
    public void dispose() {

    }
}
