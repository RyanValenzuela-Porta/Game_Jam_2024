package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu {

    Texture start1;
    Texture start2;
    SpriteBatch screen;
    boolean state;
    int x;

    public Menu(SpriteBatch screen) {
        start1 = new Texture("menu1.png");
        start2 = new Texture("menu2.png");
        state = true;
        x = 0;
        this.screen = screen;
    }

    public void render() {
        if (state) {
            screen.draw(start1, 0, 0);
            x++;
            if (x == 30) {
                state = false;
            }
        } else {
            screen.draw(start2, 0, 0);
            x--;
            if (x == 0) {
                state = true;
            }
        }
    }

    public boolean check() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            return true;
        }
        return false;
    }
}