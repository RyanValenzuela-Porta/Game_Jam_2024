package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffects {
    
    Sound sound;
    boolean x = true;
    public SoundEffects(){
        
    }

    public void swordplay(){
        sound = Gdx.audio.newSound(Gdx.files.internal("swordAttack.mp3"));
        if(x){
            sound.play(1.0f);
            x = false;
        }
    }

    public void restart(){
        x = true;
    }

    public void dispose(){
        sound.dispose();
    }
}
