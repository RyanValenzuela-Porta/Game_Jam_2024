package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffects {
    
    Sound swordsound;
    Sound monsterDeath;
    boolean swordreset = true;
    public SoundEffects(){
        
    }

    public void swordplay(){
        swordsound = Gdx.audio.newSound(Gdx.files.internal("swordAttack.mp3"));
        if(swordreset){
            swordsound.play(1.0f);
            swordreset = false;
        }
    }

    public void monsterDeath(){
        monsterDeath = Gdx.audio.newSound(Gdx.files.internal("monsterDeath.mp3"));
        monsterDeath.play(1.0f);
    }

    public void restart(){
        swordreset = true;
    }

    public void dispose(){
        swordsound.dispose();
    }
}
