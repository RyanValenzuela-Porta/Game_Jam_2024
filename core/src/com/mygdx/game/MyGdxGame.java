package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import java.awt.*;

public class MyGdxGame extends Game {

	Soundtrack soundtrack;

	@Override
	public void create () {
		setScreen(new Start());
		soundtrack = new Soundtrack();
		soundtrack.load();
	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose () {
		super.dispose();
		soundtrack.dispose();
	}
}
