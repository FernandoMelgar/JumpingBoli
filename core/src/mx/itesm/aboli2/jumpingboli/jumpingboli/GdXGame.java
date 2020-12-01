package mx.itesm.aboli2.jumpingboli.jumpingboli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

import mx.itesm.aboli2.jumpingboli.jumpingboli.button.ButtonFactory;
import mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView;


public class GdXGame extends Game {
    private AssetManager manager;
    public mx.itesm.aboli2.jumpingboli.jumpingboli.button.ButtonFactory buttonFactory;
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

