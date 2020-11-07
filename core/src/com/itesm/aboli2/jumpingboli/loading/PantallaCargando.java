package com.itesm.aboli2.jumpingboli.loading;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.Pantallas;
import com.itesm.aboli2.jumpingboli.about.AboutView;
import com.itesm.aboli2.jumpingboli.configuration.ConfigurationView;
import com.itesm.aboli2.jumpingboli.game.Boli;
import com.itesm.aboli2.jumpingboli.game.GameView;
import com.itesm.aboli2.jumpingboli.howTo.howToView;
import com.itesm.aboli2.jumpingboli.menu.MenuView;
import com.itesm.aboli2.jumpingboli.skins.SkinsView;

public class PantallaCargando extends Pantalla {

    //Animación de cargando.

    //Asset Manager
    private AssetManager manager;

    //Referencia al juego

    //Las pantallas a manejar
    private Pantallas siguientePantalla;

    //Indicador de avance de carga
    private int avance;

    public PantallaCargando(GdXGame game, Pantallas siguientePantalla) {
        super(game);
        this.siguientePantalla = siguientePantalla;
    }

    @Override
    public void show() {
        cargarRecursos();
    }

    private void cargarRecursos() {
        manager = game.getManager();
        switch(siguientePantalla){
            case MENU:
                cargarRecursosMenu();
                break;
            case NIVELUNO:
                cargarRecursosNivelUno();
                break;
            case ABOUT:
                cargarRecursosAbout();
                break;
            case SKINS:
                cargarRecursosSkins();
                break;
            case CONFIGURACION:
                cargarRecursosConfiguracion();
                break;
            case HOWTO:
                cargarRecursosHowTo();
                break;

        }
    }

    private void cargarRecursosHowTo() {

    }

    private void cargarRecursosConfiguracion() {

    }

    private void cargarRecursosSkins() {

    }

    private void cargarRecursosAbout() {
        //Fondo
        manager.load("fondos/fondoAbout.png", Texture.class);
        //Botones
        manager.load("buttons/btnFlechaArriba.png", Texture.class);
        manager.load("buttons/btnFlechaArribaPicado.png", Texture.class);
        manager.load("buttons/btnFlecha.png", Texture.class);
        manager.load("buttons/btnFlechaPicado.png", Texture.class);
    }

    private void cargarRecursosNivelUno() {
        //En este mapa no se cargan escudos ya que no hay enemigos que les den utilidad.
        //Fondo
        manager.load("mapas/NivelUno.png", Texture.class);
        //Boli
        manager.load("characters/boli_morado.png", Boli.class);
        //Botones
        manager.load("buttons/btnPause.png", Texture.class);
        manager.load("buttons/boton_128.png", Texture.class);
        //Texto
        manager.load("fuentes/exoFont.fnt", GameText.class);
        // Mapa
        manager.load("mapas/NivelUno.png", TiledMap.class);
        //Audio
        manager.load("music/MusicaFondoNivel1.mp3", Music.class);
    }

    private void cargarRecursosMenu() {
        //Cargamos el fondo
        manager.load("fondos/FondoPrincipal.png", Texture.class);
        //Cargamos los botones y escena.
        manager.load("buttons/btnAjustes.png", Texture.class);
        manager.load("buttons/btnComo.png", Texture.class);
        manager.load("buttons/btnComoPicado.png", Texture.class);
        manager.load("buttons/btnSkins.png", Texture.class);
        manager.load("buttons/btnSkinsPicado.png", Texture.class);
        manager.load("buttons/btnAcerca.png", Texture.class);
        manager.load("buttons/btnAcercaPicado.png", Texture.class);
        //Cargamos el título
        manager.load("titles/title.png", Texture.class);
        manager.load("titles/titleHover.png", Texture.class);
    }

    @Override
    public void render(float delta) {
        cleanScreen();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.end();

        actualizarCarga();
    }

    private void actualizarCarga() {

        if(manager.update()){
            switch(siguientePantalla){
                case MENU:
                    game.setScreen(new MenuView(game));
                    break;
                case NIVELUNO:
                    //todo: Modificar al nuevo tipo de juego.
                    game.setScreen(new GameView(game));
                    break;
                case ABOUT:
                    game.setScreen(new AboutView(game));
                    break;
                case SKINS:
                    game.setScreen(new SkinsView(game));
                    break;
                case CONFIGURACION:
                    game.setScreen(new ConfigurationView(game));
                    break;
                case HOWTO:
                    game.setScreen(new howToView(game));
                    break;
            }
        }
        avance = (int)(manager.getProgress()*100);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
