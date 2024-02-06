package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Enemies {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    String[][][] waveList = {{{ "Pumpkin", "5" }, { "Zombie", "5" }, { "Boss", "0" }},
                             {{ "Pumpkin", "0" }, { "Zombie", "5" }, { "Archer", "0" } },
                             {{ "smallEnemy", "5" }, { "BiggerMonster", "5" } },
                             {{ "smallEnemy", "0" }, { "BiggerMonster", "2" }, { "Archer", "0" } },
                            {{ "Boss", "1" } } };

    SpriteBatch batch;
    SpriteBatch hudBatch;
    Player player;
    Sword sword;
    int aliveCount;
    SoundEffects sound = new SoundEffects();
    checkCollidable collisionDetector;
    boolean bossSpawn = false;
    Boss boss;

    public Enemies(SpriteBatch newBatch, SpriteBatch newHudBatch, Player newPlayer, Sword newSword,
            ShapeRenderer shapeRenderer) {
        batch = newBatch;
        player = newPlayer;
        sword = newSword;
        hudBatch = newHudBatch;
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
                case "Boss":
                    boss = new Boss(batch, hudBatch, player);
                    for (int k = 0; k < Integer.valueOf(waveList[wave][j][1]); k++) {
                        enemies.add(boss);
                        enemies.add(new BossSword(batch, boss));
                        bossSpawn = true;
                    }

                    break;

                case "BiggerMonster":
                    for (int k = 0; k < Integer.valueOf(waveList[wave][j][1]); k++) {
                        enemies.add(new BiggerMonster(batch));
                    }
                    break;
                case "smallEnemy":
                    for (int k = 0; k < Integer.valueOf(waveList[wave][j][1]); k++) {
                        enemies.add(new smallEnemy(batch));
                    }
                    break;
            }
        }
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
        // System.out.println(enemies.toString());
        enemies.forEach(enemyToSpawn -> enemyToSpawn.draw(player.getPlayerX(), player.getPlayerY()));

        collisionDetector.checkEnemyPlayerCollision();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHp() < 0) {
                enemies.get(i).setAlive(false);
            }
        }
    }

    public void drawEnemyHUD() {
        if (bossSpawn) {
            boss.drawHealthBar();
        }

    }

    public boolean isBossDead() {
        return boss.isBossDead();
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

    public Boss getBoss() {
        return boss;
    }
}
