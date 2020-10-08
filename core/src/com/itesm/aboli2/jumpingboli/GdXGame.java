package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Game;
import com.itesm.aboli2.jumpingboli.menu.MenuView;


public class GdXGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuView(this));
    }

    @Override
    public void render() {
        super.render();
    }
}

