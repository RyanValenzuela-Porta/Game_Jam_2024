package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.*;

public class Enemy {
    private static final int cols = 9, rows = 1;
    private SpriteBatch batch;
    private Texture img;
    Animation<TextureRegion> walkAnimation;
	Texture walkSheet;
	float stateTime;

    public Enemy(SpriteBatch newBatch, Texture newImg) {
		batch = newBatch;
		img = newImg;
        createIdleAnimation();
	}

    public void draw(){
        img = new Texture("tileset.png");
        stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime,true);
        batch.draw(currentFrame, 600, 400);
    }

    public void createIdleAnimation(){
        walkSheet = new Texture(Gdx.files.internal("enemyWalksheet.png"));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,    //Splits walksheet into individual frames
                                    walkSheet.getWidth()/ cols,
                                    walkSheet.getHeight()/ rows);

        TextureRegion[] walkFrames = new TextureRegion[cols * rows];
        int index = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.11f, walkFrames);
        stateTime = 0f;
    }

    public void dispose() {
		img.dispose();
		walkSheet.dispose();
	}
}
