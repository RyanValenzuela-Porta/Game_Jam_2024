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

    public checkCollidable(Player newPlayer, ArrayList<Enemy> newEnemies, Sword newSword) {
        player = newPlayer;
        enemiesArray = newEnemies;
        swordObject = newSword;
    }

    public checkCollidable(Player newPlayer, Rock newRock) {
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

                if (!player.isInvincible()) {
                    player.setInvincibiity(true);
                    player.increaseHP(-1 * enemiesArray.get(i).getDmg()); // IF POSITIVE U ARE INVINCIBLE
                }
            }
            // if enemy collides with enemy
            for (int k = i + 1; k < enemiesArray.size(); k++) {
                if (enemiesArray.get(k).getHp() > 0
                        && enemiesArray.get(i).getHitbox().overlaps(enemiesArray.get(k).getHitbox())) {
                    enemiesArray.get(i).setX(enemiesArray.get(i).getPrevX());
                    enemiesArray.get(i).setY(enemiesArray.get(i).getPrevY());
                }
            }
        }
        return false;
    }

    public boolean checkProjectilePlayerCollision(Circle rockHitbox) {

        if (Intersector.overlaps(rockHitbox, player.getHitbox())) {
            player.setState(true);
            if (!player.isInvincible()) {
                player.setInvincibiity(true);
                player.increaseHP(-1); // IF POSITIVE U ARE
            }
            return true;
        }
        return false;
    }

}
