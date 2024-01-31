package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;

public class Soundtrack {
    Music music;

    public void load(){
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();
    }

    public void dispose(){
        music.dispose();
    }
}
