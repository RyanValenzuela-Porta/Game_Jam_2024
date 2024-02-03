package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class checkCollidable {
    
    Player player;
    ArrayList<Enemy> enemiesArray;
    Sword swordObject;
    Rock rock;

    public checkCollidable(Player newPlayer, ArrayList<Enemy> newEnemies, Sword newSword){
        player = newPlayer;
        enemiesArray = newEnemies;
        swordObject = newSword;
    }

    public checkCollidable(Player newPlayer, Rock newRock){
        player = newPlayer;
        rock = newRock;
    }

    public boolean checkEnemyPlayerCollision() {
        for (int i = 0; i < enemiesArray.size(); i++) {
            // if sword hits enemy
            if (enemiesArray.get(i).getHitbox().overlaps(swordObject.getHitbox()) && swordObject.isSwung()) {
                enemiesArray.get(i).setHp(enemiesArray.get(i).getHp() - player.getDmg());
                return true;
            }
            // if enemy hits player
            if (enemiesArray.get(i).getHitbox().overlaps(player.getHitbox()) && enemiesArray.get(i).getHp() > 0) {
                player.setState(true);
                player.increaseHP(1); //IF POSITIVE U ARE INVINCIBLE
            }
        }
        return false;
    }

    public boolean checkProjectilePlayerCollision(Circle hitbox){
        
        if (Intersector.overlaps(hitbox, player.getHitbox())) {
            player.setState(true);
            player.increaseHP(-1); //IF POSITIVE U ARE INVINCIBLE
            return true;
        }
        return false;
    }


}
