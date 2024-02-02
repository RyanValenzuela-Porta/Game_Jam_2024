package com.mygdx.game;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Enemies {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    String[][][] waveList = {{{"EnemyType1", "10"},{"EnemyType2", "10"}, {"EnemyType3", "10"}}, 
                             {{"EnemyType1", "10"},{"EnemyType2", "10"}, {"EnemyType3", "10"}}, 
                             {{"EnemyType1", "10"},{"EnemyType2", "10"}, {"EnemyType3", "10"}}};
    //HashMap<Enemy,Integer> numberOfEnemies = new HashMap<Enemy,Integer>();
    SpriteBatch batch;
    Player player;
    public Enemies(SpriteBatch newBatch, Player newPlayer){
        batch = newBatch;
        player = newPlayer;
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

    public void draw(){
        enemies.forEach(enemyToSpawn -> enemyToSpawn.draw(player.getPlayerX(), player.getPlayerY()));
    }
}
