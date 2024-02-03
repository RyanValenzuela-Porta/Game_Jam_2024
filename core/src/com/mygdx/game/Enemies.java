package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Enemies {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    String[][][] waveList = { { { "Pumpkin", "0" }, { "Zombie", "0" }, { "Archer", "2" } },
            { { "Zombie", "5" }, { "Zombie", "0" }, { "Zombie", "0" } } };

    SpriteBatch batch;
    Player player;
    Sword sword;
    int aliveCount;
    ShapeRenderer renderer;


    public Enemies(SpriteBatch newBatch, Player newPlayer, Sword newSword, ShapeRenderer shapeRenderer) {
        batch = newBatch;
        player = newPlayer;
        sword = newSword;
        renderer = shapeRenderer;
    }

    public void spawnEnemies(int wave) {
        for (int j = 0; j < waveList[wave].length; j++) {
            switch (waveList[wave][j][0]) {
                case "Pumpkin":
                    for (int k = 0; k < Integer.valueOf(waveList[wave][j][1]); k++) {
                        enemies.add(new Pumpkin(batch));
                    }
                    break;

                case "Zombie":
                    for (int k = 0; k < Integer.valueOf(waveList[wave][j][1]); k++) {
                        enemies.add(new Zombie(batch));
                    }
                    break;
                case "Archer":
                    for (int k = 0; k < Integer.valueOf(waveList[wave][j][1]); k++) {
                        enemies.add(new Archer(batch, renderer));
                    }
                    break;
            }
        }
    }

    public boolean checkCollision() {
        for (int i = 0; i < enemies.size(); i++) {
            // if sword hits enemy
            if (enemies.get(i).getHitbox().overlaps(sword.getHitbox()) && sword.isSwung()) {
                enemies.get(i).setHp(enemies.get(i).getHp() - player.getDmg());
                return true;
            }
            // if enemy hits player
            if (enemies.get(i).getHitbox().overlaps(player.getHitbox()) && enemies.get(i).getHp() > 0) {
                player.setState(true);
                player.increaseHP(1);
            }
        }
        return false;
    }

    public int countAliveEnemies() {
        aliveCount = 0;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getAlive()) {
                aliveCount += 1;
            }
        }
        return aliveCount;
    }

    public void draw() {
        enemies.forEach(enemyToSpawn -> enemyToSpawn.draw(player.getPlayerX(), player.getPlayerY()));

        checkCollision();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHp() < 0) {
                enemies.get(i).setAlive(false);
            }
        }
    }

    public boolean checkEndOfWave() {
        if (countAliveEnemies() == 0) {
            enemies.clear();
            return true;
        } else {
            return false;
        }
    }

    public void drawHitbox(ShapeRenderer renderer){
        enemies.forEach(enemyToSpawn -> enemyToSpawn.drawHitbox(renderer));
    }
}
