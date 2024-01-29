package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Player {
    String name;
    SpriteBatch batch;
    Texture img;
    public Player(String newName, SpriteBatch newBatch, Texture newImg){
        name = newName;
        batch = newBatch;
        img = newImg;
    }
    public void draw(){
        img = new Texture("badlogic.jpg");
        batch.draw(img, 0, 0);
    }
}
