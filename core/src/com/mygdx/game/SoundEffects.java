package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffects {
    
    Sound sound;
    public SoundEffects(){
        
    }

    public void swordplay(){
        sound = Gdx.audio.newSound(Gdx.files.internal("swordAttack.mp3"));
        sound.play(1.0f);
    }

    public void dispose(){
        sound.dispose();
    }
}
