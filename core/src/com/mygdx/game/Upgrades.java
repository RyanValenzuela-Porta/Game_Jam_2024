package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Upgrades {
    private SpriteBatch batch;
    private Player player;
    private Texture leftUpgrade = new Texture("leftupgrade.png");
    private Texture rightUpgrade = new Texture("rightupgrade.png");

    private float leftX;
    private float Y;
    private float rightX;

    public Upgrades(SpriteBatch newBatch, Player newPlayer) {
        batch = newBatch;
        player = newPlayer;
    }

    public void draw() {
        Y = 327;
        leftX = 385;
        rightX = 555;
        batch.draw(leftUpgrade, leftX, Y);
        batch.draw(rightUpgrade, rightX, Y);
    }

    public void leftHovered() {
        leftUpgrade = new Texture("leftselected.png");
    }

    public void leftNotHovered() {
        leftUpgrade = new Texture("leftupgrade.png");
    }

    public void rightHovered() {
        rightUpgrade = new Texture("rightselected.png");
    }

    public void rightNotHovered() {
        rightUpgrade = new Texture("rightupgrade.png");
    }

    public float getY() {
        return Y;
    }

    public float getLeftX() {
        return leftX;
    }

    public float getRightX() {
        return rightX;
    }
}
