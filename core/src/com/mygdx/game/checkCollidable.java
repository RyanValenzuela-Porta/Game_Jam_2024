package com.mygdx.game;

public class checkCollidable {
    
    Player player;
    Enemies enemiesObject;
    Sword swordObject;

    public checkCollidable(Player newPlayer, Enemies newEnemies, Sword newSword){
        player = newPlayer;
        enemiesObject = newEnemies;
        swordObject = newSword;
    }

    public boolean checkEnemyPlayerCollision() {
        for (int i = 0; i < enemiesObject.enemies.size(); i++) {
            // if sword hits enemy
            if (enemiesObject.enemies.get(i).getHitbox().overlaps(swordObject.getHitbox()) && swordObject.isSwung()) {
                enemiesObject.enemies.get(i).setHp(enemiesObject.enemies.get(i).getHp() - player.getDmg());
                return true;
            }
            // if enemy hits player
            if (enemiesObject.enemies.get(i).getHitbox().overlaps(player.getHitbox()) && enemiesObject.enemies.get(i).getHp() > 0) {
                player.setState(true);
                player.increaseHP(1); //IF POSITIVE U ARE INVINCIBLE
            }
        }
        return false;
    }


}
