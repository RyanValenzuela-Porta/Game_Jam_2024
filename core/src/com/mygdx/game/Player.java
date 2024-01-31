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
        img = new Texture("tileset.png");
        batch.draw(img, 50, 50,126,237,18,18);
        // batch.draw(img, 0, 0,0,0,100,100);
    }
}
