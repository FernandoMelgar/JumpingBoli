package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.itesm.aboli2.jumpingboli.Pause.PauseView;
import com.itesm.aboli2.jumpingboli.game.GameView;
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

