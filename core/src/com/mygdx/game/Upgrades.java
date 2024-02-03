package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;

public class Upgrades {
    private SpriteBatch batch;
    private Player player;
    private Texture leftUpgrade = new Texture("leftupgrade.png");
    private Boolean leftHovered = false;
    private Texture rightUpgrade = new Texture("rightupgrade.png");
    private Boolean rightHovered = false;
    private Rectangle leftHitbox;
    private Rectangle rightHitbox;
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
        leftHitbox = new Rectangle(100, 100, leftX, Y);
        batch.draw(rightUpgrade, rightX, Y);
        rightHitbox = new Rectangle(100, 100, rightX, Y);
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