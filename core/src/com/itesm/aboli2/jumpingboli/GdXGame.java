package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.loading.LoadingView;


public class GdXGame extends Game {
    private AssetManager manager;
    public ButtonFactory buttonFactory;
    //Creamos el asset manager que va a manejar todos los recursos.

    @Override
    public void create() {
        manager = new AssetManager();
        buttonFactory = new ButtonFactory(this);
        setScreen(new LoadingView(this, Pantallas.MENU));
    }

    public AssetManager getManager() {
        return manager;
    }


    @Override
    public void render() {
        super.render();
    }

}

