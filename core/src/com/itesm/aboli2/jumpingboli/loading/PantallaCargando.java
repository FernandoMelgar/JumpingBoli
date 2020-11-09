package com.itesm.aboli2.jumpingboli.loading;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.Pantallas;
import com.itesm.aboli2.jumpingboli.about.AboutView;
import com.itesm.aboli2.jumpingboli.configuration.ConfigurationView;
import com.itesm.aboli2.jumpingboli.game.GameView;
import com.itesm.aboli2.jumpingboli.howTo.howToView;
import com.itesm.aboli2.jumpingboli.menu.MenuView;
import com.itesm.aboli2.jumpingboli.skins.SkinsView;

public class PantallaCargando extends Pantalla {

    //Animación de cargando.
    // Animación cargando (espera...)
    private Sprite spriteCargando;
    public static final float TIEMPO_ENTRE_FRAMES = 0.05f;
    private float timerAnimacion = TIEMPO_ENTRE_FRAMES;
    private Texture texturaCargando;

    //Asset Manager
    private AssetManager manager;

    //Referencia al juego

    //Las pantallas a manejar
    private Pantallas siguientePantalla;

    //Indicador de avance de carga
    private int avance;

    // Para mostrar mensajes
    private GameText texto;
    private Texture texturaFondoCarga;
    private Texture texturaCargandoBolita;
    private Sprite spriteCargandoBolita;
    private Texture texturaCargandoEngranaje;
    private Sprite spriteCargandoEngranaje;

    public PantallaCargando(GdXGame game, Pantallas siguientePantalla) {
        super(game);
        this.siguientePantalla = siguientePantalla;
    }

    @Override
    public void show() {
        texturaCargandoEngranaje = new Texture("iconosCargando/engranaje.png");
        spriteCargandoEngranaje = new Sprite(texturaCargando);
        spriteCargandoEngranaje.setPosition(ANCHO_PANTALLA/2 - spriteCargandoEngranaje.getWidth()/2,
                ALTO_PANTALLA/2 - spriteCargandoEngranaje.getHeight()/2);
        texturaCargandoBolita = new Texture("iconosCargando/bolita.png");
        spriteCargandoBolita = new Sprite(texturaCargandoBolita);
        spriteCargandoBolita.setPosition(ANCHO_PANTALLA/2 - spriteCargandoBolita.getWidth()/2,
                ALTO_PANTALLA/2 - spriteCargandoBolita.getHeight()/2);
        texturaFondoCarga = new Texture("fondos/fondoCargando.png");
        //texto = new Texto("fuentes/exoFont.fnt");
        texto = new GameText("runner/game.fnt");
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
        manager.load("characters/boli_morado.png", Texture.class);
        manager.load("characters/boliVerde.png", Texture.class);
        manager.load("characters/boliAzul.png", Texture.class);
        manager.load("characters/boliRoja.png", Texture.class);

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
        manager.load("characters/boli_morado.png", Texture.class);
        manager.load("characters/boliVerde.png", Texture.class);
        manager.load("characters/boliAzul.png", Texture.class);
        manager.load("characters/boliRoja.png", Texture.class);
        //Botones
        manager.load("buttons/btnPause.png", Texture.class);
        manager.load("buttons/boton_128.png", Texture.class);
        //Texto
        // Mapa
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("mapas/NivelUno.tmx", TiledMap.class);
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

        for (int i = 0; i < 3; i++) {
            batch.draw(texturaFondoCarga,ANCHO_PANTALLA * i,0);
        }
        //batch.draw(texturaFondoCarga,0,0);
        //batch.draw(texturaFondoCarga, ANCHO,0);
        spriteCargandoEngranaje.draw(batch);
        spriteCargandoBolita.draw(batch);
        //texto.mostrarMensaje(batch, avance + "%", ANCHO/2, ALTO/1.9f);
        texto.mostrarMensaje(batch, avance + "%", camera.position.x, camera.position.y + ALTO_PANTALLA/2-80);


        batch.end();

        // Actualizar
        timerAnimacion -= delta;
        if (timerAnimacion<=0) {
            timerAnimacion = TIEMPO_ENTRE_FRAMES;
            spriteCargandoEngranaje.rotate(15);
            spriteCargandoBolita.rotate(20);
        }

        actualizarCarga();
        actualizarCamara();
        spriteCargandoEngranaje.setPosition(camera.position.x- spriteCargandoEngranaje.getWidth()/2,
                camera.position.y- spriteCargandoEngranaje.getHeight()/2);
        spriteCargandoBolita.setPosition(camera.position.x- spriteCargandoBolita.getWidth()/2,
                camera.position.y- spriteCargandoBolita.getHeight()/2);

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

    private void actualizarCamara() {
        camera.position.x = camera.position.x + 2;
        camera.update();
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
