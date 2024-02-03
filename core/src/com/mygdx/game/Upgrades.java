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
    private Texture upgradeText = new Texture("upgradeText.png");
    private Texture[] benefitPic = new Texture[2];
    private Texture[] downsidePic = new Texture[2];
    private int[] generatedLeft = { 5, 5 };
    private int[] generatedRight = { 5, 5 };
    private int benefitL;
    private int downsideL;
    private int benefitR;
    private int downsideR;

    private float leftX;
    private float Y;
    private float rightX;

    public Upgrades(SpriteBatch newBatch, Player newPlayer) {
        batch = newBatch;
        player = newPlayer;
    }

    public void generateUpgradeLeft() {
        if (generatedLeft[1] == 5) {
            Random rand = new Random();
            benefitL = rand.nextInt(3);
            // ensure benefit and downside can't be the same
            do {
                downsideL = rand.nextInt(4);
            } while (benefitL == downsideL);
            switch (benefitL) {
                case 0:
                    benefitPic[0] = new Texture("+speed.png");
                    break;
                case 1:
                    benefitPic[0] = new Texture("+hp.png");
                    break;
                case 2:
                    benefitPic[0] = new Texture("+dmg.png");
                    break;
            }
            switch (downsideL) {
                case 0:
                    downsidePic[0] = new Texture("-speed.png");
                    break;
                case 1:
                    downsidePic[0] = new Texture("-hp.png");
                    break;
                case 2:
                    downsidePic[0] = new Texture("-dmg.png");
                    break;
                case 3:
                    downsidePic[0] = new Texture("+size.png");
            }
            generatedLeft[0] = benefitL;
            generatedLeft[1] = downsideL;
        }
        batch.draw(benefitPic[0], 420, 150);
        batch.draw(downsidePic[0], 420, 125);

    }

    public void generateUpgradeRight() {
        if (generatedRight[1] == 5) {
            Random rand = new Random();
            benefitR = rand.nextInt(3);
            // ensure benefit and downside can't be the same
            do {
                downsideR = rand.nextInt(4);
            } while (benefitR == downsideR);
            switch (benefitR) {
                case 0:
                    benefitPic[1] = new Texture("+speed.png");
                    break;
                case 1:
                    benefitPic[1] = new Texture("+hp.png");
                    break;
                case 2:
                    benefitPic[1] = new Texture("+dmg.png");
                    break;
            }
            switch (downsideR) {
                case 0:
                    downsidePic[1] = new Texture("-speed.png");
                    break;
                case 1:
                    downsidePic[1] = new Texture("-hp.png");
                    break;
                case 2:
                    downsidePic[1] = new Texture("-dmg.png");
                    break;
                case 3:
                    downsidePic[1] = new Texture("+size.png");
            }
            generatedRight[0] = benefitR;
            generatedRight[1] = downsideR;
        }
        batch.draw(benefitPic[1], 600, 150);
        batch.draw(downsidePic[1], 600, 125);
    }

    public void applyUpgradeL() {
        switch (benefitR) {
            case 0:
                player.increaseSpeed(50);
                System.out.println("+speed");
                break;
            case 1:
                player.increaseHPUpgrade(2);
                System.out.println("+hp");
                break;
            case 2:
                player.increaseDmg(5);
                System.out.println("+dmg");
                break;
        }
        switch (downsideL) {
            case 0:
                player.increaseSpeed(-50);
                System.out.println("-speed");
                break;
            case 1:
                player.increaseHPUpgrade(-2);
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
        generatedLeft[1] = 5;
    }

    public void applyUpgradeR() {
        switch (benefitR) {
            case 0:
                player.increaseSpeed(50);
                System.out.println("+speed");
                break;
            case 1:
                player.increaseHPUpgrade(2);
                System.out.println("+hp");
                break;
            case 2:
                player.increaseDmg(5);
                System.out.println("+dmg");
                break;
        }
        switch (downsideR) {
            case 0:
                player.increaseSpeed(-50);
                System.out.println("-speed");
                break;
            case 1:
                player.increaseHPUpgrade(-2);
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
        generatedRight[1] = 5;
    }

    public void draw() {
        Y = 327;
        leftX = 385;
        rightX = 555;
        batch.draw(leftUpgrade, leftX, Y);
        batch.draw(rightUpgrade, rightX, Y);
        batch.draw(upgradeText, 420, 300);
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
