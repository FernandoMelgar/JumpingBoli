package mx.itesm.aboli2.jumpingboli.jumpingboli.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import mx.itesm.aboli2.jumpingboli.jumpingboli.GameText;
import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantalla;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantallas;
import mx.itesm.aboli2.jumpingboli.jumpingboli.about.AboutView;
import mx.itesm.aboli2.jumpingboli.jumpingboli.configuration.ConfigurationView;
import mx.itesm.aboli2.jumpingboli.jumpingboli.game.views.GameView;
import mx.itesm.aboli2.jumpingboli.jumpingboli.game.views.LevelSelectionView;
import mx.itesm.aboli2.jumpingboli.jumpingboli.howTo.howToView;
import mx.itesm.aboli2.jumpingboli.jumpingboli.menu.MenuView;
import mx.itesm.aboli2.jumpingboli.jumpingboli.skins.SkinsView;

public class LoadingView extends Pantalla {

    //Animación de cargando.
    // Animación cargando (espera...)
    private Sprite spriteCargando;
    public static final float TIEMPO_ENTRE_FRAMES = 0.05f;
    private float timerAnimacion = TIEMPO_ENTRE_FRAMES;

    //Asset Manager
    private AssetManager manager;

    //Referencia al juego

    //Las pantallas a manejar
    private Pantallas siguientePantalla;

    //Indicador de avance de carga
    private int avance;

    // Para mostrar mensajes
    private mx.itesm.aboli2.jumpingboli.jumpingboli.GameText texto;
    private Texture texturaFondoCarga;
    private Texture texturaCargandoBolita;
    private Sprite spriteCargandoBolita;
    private Texture texturaCargandoEngranaje;
    private Sprite spriteCargandoEngranaje;

    public LoadingView(GdXGame game, Pantallas siguientePantalla) {
        super(game);
        this.siguientePantalla = siguientePantalla;
    }

    @Override
    public void show() {

        // Bloquea la tecla back en el celular
        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        texturaCargandoEngranaje = new Texture("iconosCargando/engranaje.png");
        spriteCargandoEngranaje = new Sprite(texturaCargandoEngranaje);
        spriteCargandoEngranaje.setPosition(ANCHO_PANTALLA / 2 - spriteCargandoEngranaje.getWidth() / 2,
                ALTO_PANTALLA / 2 - spriteCargandoEngranaje.getHeight() / 2);
        texturaCargandoBolita = new Texture("iconosCargando/bolita.png");
        spriteCargandoBolita = new Sprite(texturaCargandoBolita);
        spriteCargandoBolita.setPosition(ANCHO_PANTALLA / 2 - spriteCargandoBolita.getWidth() / 2,
                ALTO_PANTALLA / 2 - spriteCargandoBolita.getHeight() / 2);
        texturaFondoCarga = new Texture("fondos/fondoCargando.png");
        texto = new GameText("fuentes/exoFont.fnt");
        cargarRecursos();
    }

    private void cargarRecursos() {
        manager = game.getManager();

        manager.load("efectosSonido/efectoBoton.wav", Sound.class);
        manager.load("buttons/btnBack.png", Texture.class);
        manager.load("buttons/btnBackPicado.png", Texture.class);

        switch (siguientePantalla) {
            case MENU:
                cargarRecursosMenu();
                break;
            case GAME:
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
            case LEVELSELECTION:
                cargarRecursosSeleccionDeNivel();

        }
    }

    private void cargarRecursosSeleccionDeNivel() {
        manager.load("fondos/fondoPausa.png", Texture.class);
        manager.load("buttons/levelselection/btnLevel1_active.png", Texture.class);
        manager.load("buttons/levelselection/btnLevel1_clicked.png", Texture.class);
        manager.load("buttons/levelselection/btnLevel2_active.png", Texture.class);
        manager.load("buttons/levelselection/btnLevel2_clicked.png", Texture.class);
        manager.load("buttons/levelselection/btnLevel2_blocked.png", Texture.class);
        manager.load("buttons/levelselection/btnLevel2_blocked_clicked.png", Texture.class);
        manager.load("efectosSonido/playLevel.ogg", Sound.class);

    }

    private void cargarRecursosHowTo() {

    }

    private void cargarRecursosConfiguracion() {
        manager.load("efectosSonido/efectoBoton.wav", Sound.class);

    }

    private void cargarRecursosSkins() {
        manager.load("fondos/fondoSkins.png", Texture.class);
        manager.load("fondos/fondoEstrellasMovibles.png", Texture.class);
        manager.load("characters/boli_morado.png", Texture.class);
        manager.load("characters/boliVerde.png", Texture.class);
        manager.load("characters/boliAzul.png", Texture.class);
        manager.load("characters/boliRoja.png", Texture.class);
        manager.load("characters/boliMorado62.png", Texture.class);
        manager.load("characters/boliVerde62.png", Texture.class);
        manager.load("characters/boliAzul62.png", Texture.class);
        manager.load("characters/boliRoja62.png", Texture.class);
        manager.load("efectosSonido/efectoBoton.wav", Sound.class);

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
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load("mapas/platNivel1.tmx", TiledMap.class);

        //Audio
        manager.load("music/MusicaFondoNivel1.mp3", Music.class);

    }

    private void cargarRecursosMenu() {
        //Cargamos el fondo
        manager.load("fondos/FondoPrincipal.png", Texture.class);
        manager.load("fondos/fondoEstrellasMovibles.png", Texture.class);
        //Cargamos los botones y escena.
        manager.load("buttons/btnAjustes.png", Texture.class);
        manager.load("buttons/btnComo.png", Texture.class);
        manager.load("buttons/btnComoPicado.png", Texture.class);
        manager.load("buttons/btnSkins.png", Texture.class);
        manager.load("buttons/btnSkinsPicado.png", Texture.class);
        manager.load("buttons/btnAcerca.png", Texture.class);
        manager.load("buttons/btnAcercaPicado.png", Texture.class);
        manager.load("buttons/btnPlay.png", Texture.class);

        //Cargamos el título
        manager.load("titles/title.png", Texture.class);
        manager.load("titles/titleHover.png", Texture.class);
        //Cargamos efectos de sonido
        manager.load("efectosSonido/efectoInicio.wav", Sound.class);
        manager.load("efectosSonido/efectoBoton.wav", Sound.class);
        manager.load("efectosSonido/play.ogg", Sound.class);

    }

    @Override
    public void render(float delta) {
        cleanScreen();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        for (int i = 0; i <= 3; i++) {
            batch.draw(texturaFondoCarga,ANCHO_PANTALLA * i,0);
        }
        spriteCargandoEngranaje.draw(batch);
        spriteCargandoBolita.draw(batch);
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
                case GAME:
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
                case LEVELSELECTION:
                    game.setScreen(new LevelSelectionView(game));
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
