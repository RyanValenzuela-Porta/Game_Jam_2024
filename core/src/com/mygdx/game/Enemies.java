package com.mygdx.game;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
public class Enemies {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    String[][][] waveList = {{{"EnemyType1", "10"},{"EnemyType2", "10"}, {"EnemyType3", "10"}}, 
                             {{"EnemyType1", "10"},{"EnemyType2", "10"}, {"EnemyType3", "10"}}, 
                             {{"EnemyType1", "10"},{"EnemyType2", "10"}, {"EnemyType3", "10"}}};
    //HashMap<Enemy,Integer> numberOfEnemies = new HashMap<Enemy,Integer>();
    SpriteBatch batch;
    Player player;
    Sword sword;
    private AssetManager assetManager;
    private Sound sound;
    public Enemies(SpriteBatch newBatch, Player newPlayer, Sword newSword){
        batch = newBatch;
        player = newPlayer;
        sword = newSword;
        assetManager = new AssetManager();
        assetManager.load("hit.mp3", Sound.class);
        assetManager.finishLoading();
        sound = assetManager.get("hit.mp3", Sound.class);

    }
    public void spawnEnemies(int wave){
        //Goal: Have an ArrayList full of enemy objects, all the different types of enemies
        //Each wave will be defined by an arrayList called "waveList" which stores pairs of enemies and the numbers of enemies
        //Iterate through the wave arrayList and then see each pair e.g ("Zombie",10) add the enemy the specified number of times to the enemies arraylist.
        //The pairs will be strings capitalised to match the class name and an integer

        enemies.clear();
        
        
        for(int j=0; j<waveList[wave].length;j++){
            switch(waveList[wave][j][0]){
                case "EnemyType1":
                    for(int k=0; k<Integer.valueOf(waveList[wave][j][1]); k++){
                        enemies.add(new Zombie(batch));
                    }
                    break;
                
                case "EnemyType2":
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
        
        
        //for(int i=0;i<10;i++){
        //    enemies.add(new Zombie(batch));
        //}

        // enemies.add(testZombie);
    }

    public boolean checkSwordCollision(){
        for ( int i = 0; i < enemies.size() - 1; i++) {
            // if sword hits enemy
            if (enemies.get(i).getHitbox().overlaps(sword.getHitbox()) && sword.isSwung()) {
                enemies.get(i).setHp(enemies.get(i).getHp() - 20);
                return true;
            }
            // if enemy hits player
            if (enemies.get(i).getHitbox().overlaps(player.getHitbox())) {
                player.setHP(player.getHP() - 5);
                System.out.println("hit");
            }
        }
        return false;
    }


    public void draw(){
        enemies.forEach(enemyToSpawn -> enemyToSpawn.draw(player.getPlayerX(), player.getPlayerY()));
        checkSwordCollision();

        for (int i = 0; i < enemies.size() - 1; i++) {
            if (enemies.get(i).getHp() < 0) {
                enemies.get(i).setAlive(false);
            }
        }
    }
}
