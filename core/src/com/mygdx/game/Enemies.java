package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class Enemies {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    String[][][] waveList = { { { "Pumpkin", "5" }, { "Pumpkin", "0" }, { "Pumpkin", "0" } },
                              { { "Zombie", "5" }, { "Zombie", "0" }, { "Zombie", "0"}}};
    
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

        for (int j = 0; j < waveList[wave].length; j++) {
            switch (waveList[wave][j][0]) {
                case "Pumpkin":
                    for(int k=0; k<Integer.valueOf(waveList[wave][j][1]); k++){
                        enemies.add(new Pumpkin(batch));
                    }
                    break;

                case "Zombie":
                    for(int k=0; k<Integer.valueOf(waveList[wave][j][1]); k++){
                        enemies.add(new Zombie(batch));
                    }
                    break;
                case "EnemyType3":
                    for(int k=0; k<Integer.valueOf(waveList[wave][j][1]); k++){
                        enemies.add(new Zombie(batch));
                    }
                    break;

            }
        }
    }

    public boolean checkCollision() {
        for (int i = 0; i < enemies.size(); i++) {
            // if sword hits enemy
            if (enemies.get(i).getHitbox().overlaps(sword.getHitbox()) && sword.isSwung()) {
                enemies.get(i).setHp(enemies.get(i).getHp() - 20);
                return true;
            }
            // if enemy hits player
            if (enemies.get(i).getHitbox().overlaps(player.getHitbox()) && enemies.get(i).getHp() > 0) {
                player.setHP(player.getHP() - 5);
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

    public boolean checkEndOfWave(){
        if(countAliveEnemies() == 0){
            enemies.clear();
            return true;
        }else{
            return false;
        }
    }
}
