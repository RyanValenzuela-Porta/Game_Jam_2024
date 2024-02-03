package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Map {

    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    public Map(){
        create();
    }
    
    public void create(){
		tiledMap =  new TmxMapLoader().load("newmap2.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void render(OrthographicCamera camera){
        tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
    }

    public void dispose(){
        tiledMap.dispose();
    }
}
