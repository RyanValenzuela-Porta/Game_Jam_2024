package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class Enemies {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    String[][][] waveList = { { { "EnemyType1", "10" }, { "EnemyType2", "10" }, { "EnemyType3", "10" } },
            { { "EnemyType1", "10" }, { "EnemyType2", "10" }, { "EnemyType3", "10" } },
            { { "EnemyType1", "10" }, { "EnemyType2", "10" }, { "EnemyType3", "10" } } };
    // HashMap<Enemy,Integer> numberOfEnemies = new HashMap<Enemy,Integer>();
    SpriteBatch batch;

    Player player;
    Sword sword;
    int aliveCount;
    private AssetManager assetManager;
    private Sound sound;
    private int wave;

    public Enemies(SpriteBatch newBatch, Player newPlayer, Sword newSword) {
        batch = newBatch;
        player = newPlayer;
        sword = newSword;
        assetManager = new AssetManager();
    }

    public void spawnEnemies(int wave) {
        // Goal: Have an ArrayList full of enemy objects, all the different types of
        // enemies
        // Each wave will be defined by an arrayList called "waveList" which stores
        // pairs of enemies and the numbers of enemies
        // Iterate through the wave arrayList and then see each pair e.g ("Zombie",10)
        // add the enemy the specified number of times to the enemies arraylist.
        // The pairs will be strings capitalised to match the class name and an integer

        for (int j = 0; j < waveList[wave].length; j++) {
            int maxEnemies = Integer.valueOf(waveList[wave][j][1]);

            switch (waveList[wave][j][0]) {
                case "EnemyType1":
                    while (countAliveEnemies() < 3 && enemies.size() < maxEnemies) {
                        enemies.add(new Zombie(batch));
                    }
                    break;

                case "EnemyType2":
                    while (countAliveEnemies() < 3 && enemies.size() < maxEnemies) {
                        enemies.add(new Zombie(batch));
                    }
                    break;
                case "EnemyType3":
                    while (countAliveEnemies() < 3 && enemies.size() < maxEnemies) {
                        enemies.add(new Zombie(batch));
                    }
                    break;

            }
        }

        // for(int i=0;i<10;i++){
        // enemies.add(new Zombie(batch));
        // }

        // enemies.add(testZombie);
    }

    public boolean checkCollision() {
        for (int i = 0; i < enemies.size() - 1; i++) {
            // if sword hits enemy
            if (enemies.get(i).getHitbox().overlaps(sword.getHitbox()) && sword.isSwung()) {
                enemies.get(i).setHp(enemies.get(i).getHp() - 20);
                return true;
            }
            // if enemy hits player
            System.err.println(enemies.get(i).getHp());
            if (enemies.get(i).getHitbox().overlaps(player.getHitbox()) && enemies.get(i).getHp() > 0) {
                player.setHP(player.getHP() - 5);
            }
        }
        return false;
    }

    public int countAliveEnemies() {
        aliveCount = 0;
        for (int i = 0; i < enemies.size() - 1; i++) {
            if (enemies.get(i).getAlive()) {
                aliveCount += 1;
            }
        }
        return aliveCount;
    }

    public void draw() {
        enemies.forEach(enemyToSpawn -> enemyToSpawn.draw(player.getPlayerX(), player.getPlayerY()));

        checkCollision();

        for (int i = 0; i < enemies.size() - 1; i++) {
            if (enemies.get(i).getHp() < 0) {
                enemies.get(i).setAlive(false);
                System.out.println("killed 1 enemy");
                spawnEnemies(wave);
            }
        }

    }
}
