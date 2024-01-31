package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Player {
    SpriteBatch batch;
    Texture img;
    public Player( SpriteBatch newBatch, Texture newImg){
        batch = newBatch;
        img = newImg;
    }
    public void draw(){
        img = new Texture("badlogic.jpg");
        batch.draw(img, 0, 0);
    }
}
