package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.aboli2.jumpingboli.Boli;
import com.itesm.aboli2.jumpingboli.EstadoBoli;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.Texto;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class GameView extends Pantalla {

  private Stage gameStage;

  private int speedCamera = 2;

  private final int TAM_CELDA = 32;

  //Boli
  private Boli boli;

  //Mapa
  private TiledMap mapa;
  private OrthogonalTiledMapRenderer rendererMapa;

  //Cámara/vista HUD.
  private Stage escenaHUD;
  private OrthographicCamera camaraHUD;
  private Viewport vistaHUD;

  // Música
  private Music musicaFondo;

  //Manager
  private AssetManager manager;

  //Escudos
  private Texture texturaEscudo;
  private Array<Escudo> arrEscudos;

  //Texto
  private Texto texto;
  private float puntos;

  //Inicia el juego
  private EstadoJuego estado = EstadoJuego.INICIANDO;

  //TIMER
  float timerPausa;


  public GameView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
    gameStage = new Stage(super.viewport);

    manager = new AssetManager();
    crearAudio();
    crearBoli();
    crearMapa();
    crearHUD();
    crearEscudos();
    crearTexto();
    moverBoli();
    Gdx.input.setInputProcessor(escenaHUD);

  }

  private void crearTexto() {
    texto = new Texto("fuentes/exoFont.fnt");
  }

  private void moverBoli() {

  }

  private void crearBoli() {
    boli = new Boli(new Texture("characters/Boli_50.png"), 200,600);
  }

  private void crearHUD() {
    camaraHUD = new OrthographicCamera(ANCHO_PANTALLA, ALTO_PANTALLA);
    camaraHUD.position.set(ANCHO_PANTALLA/2, ALTO_PANTALLA/2, 0);
    camaraHUD.update();
    vistaHUD = new StretchViewport(ANCHO_PANTALLA, ALTO_PANTALLA, camaraHUD);
    escenaHUD = new Stage(vistaHUD);


    //Creamos el botón de pausa (configuración va dentro de pausa).
    ImageButton btnGPause = new GameButton("buttons/btnPause.png");
    btnGPause.setPosition(ANCHO_PANTALLA*0.95f, ALTO_PANTALLA*0.90f, Align.center);
    //Acción botón
    btnGPause.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        musicaFondo.dispose();
        game.setScreen(new MenuView(game));
      }
    });
    ImageButton bntSalto = new GameButton("buttons/boton_128.png");
    bntSalto.setPosition(ANCHO_PANTALLA * .9f, ALTO_PANTALLA * .1f, Align.center);
    bntSalto.addListener(new ClickListener(){

      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
          if (boli.getEstado() == EstadoBoli.RODANDO) {
            boli.setyBase(boli.getY());
            boli.saltar();
          }
      }
    });

    escenaHUD.addActor(bntSalto);
    escenaHUD.addActor(btnGPause);
  }

  private void crearEscudos() {
    texturaEscudo = new Texture("characters/escudo.png");
    arrEscudos = new Array<>();
    for(int i = 1; i <= 3; i++){
      Escudo escudo = new Escudo(texturaEscudo, i*30, ALTO_PANTALLA*0.88f);
      arrEscudos.add(escudo);
    }
  }

  private void crearMapa() {
    //Se crea el asset manager para manejar el mapa.
    manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    manager.load("mapas/platNivel1.tmx", TiledMap.class);
    manager.finishLoading();
    mapa = manager.get("mapas/platNivel1.tmx");
    rendererMapa = new OrthogonalTiledMapRenderer(mapa);

  }

  private void crearAudio() {
    manager.load("music/MusicaFondoNivel1.mp3", Music.class);
    manager.finishLoading(); //Espera
    musicaFondo = manager.get("music/MusicaFondoNivel1.mp3");
    musicaFondo.setVolume(0.1f);
    musicaFondo.setLooping(true);
    musicaFondo.play();
  }

  public void colisionPlataforma(){
    // Prueba si debe caer por llegar a un espacio vacío
      // Calcula la celda donde estaría después de moverlo
      int celdaX = (int) (boli.getX() / TAM_CELDA);
      int celdaY = (int) ((boli.getY() + boli.DY) / TAM_CELDA);

      // Recuperamos la celda en esta posición
      // La capa 0 es el fondo
      TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get(0);
      TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX, celdaY);
      TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX + 1, celdaY);
      // probar si la celda está ocupada
      if (celdaAbajo == null && celdaDerecha == null && boli.getEstado() != EstadoBoli.SALTANDO){
        if(boli.getEstado() != EstadoBoli.CAYENDO){
          boli.setyBase(boli.getY());
          boli.cayendo();
        }
      } else if(boli.getEstado() != EstadoBoli.SALTANDO) {
        // Dejarlo sobre la celda que lo detiene
        boli.setPosicion(boli.getX(),(celdaY + 1) * TAM_CELDA);
        boli.setEstadoBoli(EstadoBoli.RODANDO);
      }


  }


  @Override
  public void render(float delta) {
    actualizar();
    cleanScreen();
    moverCamara();
    colisionPlataforma();
    batch.setProjectionMatrix(camera.combined);

    rendererMapa.setView(camera);
    rendererMapa.render();

    batch.begin();
    boli.render(batch);
    batch.end();

    actualizarTimer(delta);

    // INICIANDO
    if (estado == EstadoJuego.INICIANDO){
      Gdx.app.log("INICIANDO", "Tiempo: " + (int)(timerPausa));
      batch.begin();
      texto.mostrarMensaje(batch, ""+(int)(3-timerPausa),
              ANCHO_PANTALLA/2, ALTO_PANTALLA/2);
      batch.end();
    }

    gameStage.draw();
    //HUD
    batch.setProjectionMatrix(camaraHUD.combined);
    batch.begin();
    dibujarEscudos();
    dibujarPuntaje();
    batch.end();
    escenaHUD.draw();
  }

  private void actualizarTimer(float delta) {
    timerPausa += delta;
    if (estado == EstadoJuego.INICIANDO && timerPausa>=3) {
      estado = EstadoJuego.JUGANDO;
    }
  }

  private void dibujarPuntaje() {
    int intPuntos = (int)puntos;
    texto.mostrarMensaje(batch, "" + intPuntos, ANCHO_PANTALLA*0.12f, ALTO_PANTALLA*0.915f);
  }

  private void actualizar() {
    actualizarEscudo();
    actualizarPuntos();
  }

  private void actualizarPuntos() {
    puntos+= 1;
  }

  private void actualizarEscudo() {
    for(int i = arrEscudos.size-1; i>= 0; i--){
      if(boli.getY()<0){
        arrEscudos.removeIndex(i);
        break;
      }
    }
  }

  private void dibujarEscudos() {
    for(Escudo escudo: arrEscudos){
      escudo.render(batch);
    }
  }

  private void moverCamara() {
    camera.position.x = camera.position.x + boli.getDX();
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
    batch.dispose();
  }

  class  ProcesadorEntrada implements InputProcessor {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      Vector3 v = new Vector3(screenX, screenY, 0);
      camaraHUD.unproject(v);

      if(estado == EstadoJuego.JUGANDO){
        if (v.x<=ANCHO_PANTALLA/2 + camera.position.x && boli.getEstado() == EstadoBoli.RODANDO) {
          boli.setyBase(boli.getY());
          boli.saltar();
        }
      }
      return true;    //////////////////////  **********   ///////////////////
    }

    @Override
    public boolean keyDown(int keycode) {
      return false;
    }

    @Override
    public boolean keyUp(int keycode) {
      return false;
    }

    @Override
    public boolean keyTyped(char character) {
      return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
      return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
      return false;
    }

    @Override
    public boolean scrolled(int amount) {
      return false;
    }
  }

  private enum EstadoJuego{
    JUGANDO,
    PAUSADO,
    INICIANDO,
    TERMINADO
  }

}
