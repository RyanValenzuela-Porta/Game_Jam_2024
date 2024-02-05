package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Enemies {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    String[][][] waveList = { { { "Pumpkin", "0" }, { "Zombie", "0" }, { "Archer", "1" } },
            { { "Zombie", "0" }, { "Zombie", "0" }, { "Archer", "1" } } };

    SpriteBatch batch;
    Player player;
    Sword sword;
    int aliveCount;
    SoundEffects sound = new SoundEffects();
    checkCollidable collisionDetector;

    public Enemies(SpriteBatch newBatch, Player newPlayer, Sword newSword, ShapeRenderer shapeRenderer) {
        batch = newBatch;
        player = newPlayer;
        sword = newSword;
        collisionDetector = new checkCollidable(newPlayer, enemies, newSword);
    }

    public void spawnEnemies(int wave, ShapeRenderer renderer) {
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
                        enemies.add(new Archer(batch, renderer, player));
                    }
                    break;
            }
        }
    }
    /*
     * public boolean checkCollision() {
     * for (int i = 0; i < enemies.size(); i++) {
     * // if sword hits enemy
     * if (enemies.get(i).getHitbox().overlaps(sword.getHitbox()) &&
     * sword.isSwung()) {
     * enemies.get(i).setHp(enemies.get(i).getHp() - player.getDmg());
     * sound.monsterDeath();
     * return true;
     * }
     * // if enemy hits player
     * if (enemies.get(i).getHitbox().overlaps(player.getHitbox()) &&
     * enemies.get(i).getHp() > 0) {
     * player.setState(true);
     * 
     * if (!player.isInvincible()) {
     * player.setInvincibiity(true);
     * player.increaseHP(-1);
     * }
     * }
     * 
     * // if enemy collides with enemy
     * for (int k = i + 1; k < enemies.size(); k++) {
     * if (enemies.get(i).getHitbox().overlaps(enemies.get(k).getHitbox())) {
     * enemies.get(i).setX(enemies.get(i).getPrevX());
     * enemies.get(i).setY(enemies.get(i).getPrevY());
     * }
     * }
     * 
     * }
     * return false;
     * }
     */

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

        collisionDetector.checkEnemyPlayerCollision();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHp() < 0) {
                enemies.get(i).setAlive(false);
            }
        }
    }

    public void drawHitboxes(ShapeRenderer shapeRenderer) {
        enemies.forEach(enemyToSpawn -> enemyToSpawn.drawHitbox(shapeRenderer));
    }

    public boolean checkEndOfWave() {
        if (countAliveEnemies() == 0) {
            enemies.clear();
            return true;
        } else {
            return false;
        }
    }

    public void drawHitbox(ShapeRenderer renderer) {
        enemies.forEach(enemyToSpawn -> enemyToSpawn.drawHitbox(renderer));
    }
}
