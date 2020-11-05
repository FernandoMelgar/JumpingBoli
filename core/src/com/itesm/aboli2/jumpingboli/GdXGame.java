package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.itesm.aboli2.jumpingboli.Pause.PauseView;
import com.itesm.aboli2.jumpingboli.game.GameView;
import com.itesm.aboli2.jumpingboli.loading.PantallaCargando;
import com.itesm.aboli2.jumpingboli.menu.MenuView;


public class GdXGame extends Game {
    private AssetManager manager; //Creamos el asset manager que va a manejar todos los recursos.

    @Override
    public void create() {
        manager = new AssetManager();
        setScreen(new MenuView(this));
        setScreen(new PantallaCargando(this, Pantallas.MENU));
    }

    public AssetManager getManager(){
        return manager;
    }

    @Override
    public void render() {
        super.render();
    }

}

