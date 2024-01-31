package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board {
    SpriteBatch batch;
    Texture img;
    public Board(SpriteBatch newBatch, Texture newImg){
        batch = newBatch;
        img = newImg;
    }
    public void draw(){
        //img = new Texture("totoro_decal_whitebg-removebg-preview.png");
        //batch.draw(img, 0, 0);
    }
}
