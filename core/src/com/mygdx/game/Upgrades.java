package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Random;

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

    public void generateUpgrade() {
        int benefit;
        int downside;
        Random rand = new Random();
        benefit = rand.nextInt(3);
        do {
            downside = rand.nextInt(4);
        } while (benefit == downside);
        switch (benefit) {
            case 0:
                player.increaseSpeed(50);
                System.out.println("+speed");
                break;
            case 1:
                player.increaseHP(2);
                System.out.println("+hp");
                break;
            case 2:
                player.increaseDmg(5);
                System.out.println("+dmg");
                break;
        }
        switch (downside) {
            case 0:
                player.increaseSpeed(-100);
                System.out.println("-speed");
                break;
            case 1:
                player.increaseHP(-2);
                System.out.println("-hp");
                break;
            case 2:
                player.increaseDmg(-5);
                System.out.println("-dmg");
                break;
            case 3:
                player.increaseHitbox(8);
                System.out.println("-hitbox");

        }
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
