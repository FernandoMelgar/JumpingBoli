package com.itesm.aboli2.jumpingboli.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.game.Boli;
import com.itesm.aboli2.jumpingboli.game.Escudo;
import com.itesm.aboli2.jumpingboli.game.EstadoBoli;
import com.itesm.aboli2.jumpingboli.game.EstadoBuff;
import com.itesm.aboli2.jumpingboli.win.escapeView;

public class GameView extends Pantalla {

  private Stage gameStage;
  private float nivelEscogido;

  private float velocidadCamara = 1;

  private final int TAM_CELDA = 32;
  private float contadorFondo = 0;

  //Boli
  private Boli boli;
  private float colorBoli;
  private float monedas = 0;

  //Mapa
  private TiledMap mapa;
  private OrthogonalTiledMapRenderer rendererMapa;

  //fondo
  private Texture texturaFondo;


  //Cámara/vista HUD.
  private Stage escenaHUD;
  private OrthographicCamera camaraHUD;
  private Viewport vistaHUD;

  // Música
  private Music musicaFondo;
  private boolean playMusic;

  //Escudos
  private Texture texturaEscudo;
  private Array<Escudo> arrEscudos;
  private boolean quitarEscudo = false;

  //Texto
  private GameText gameText;
  private float puntos;

  //Inicia el juego
   public EstadoJuego estado = EstadoJuego.INICIANDO;


  //TIMER
  float timerPausa;
  float timerBuffMultiplicador = 0;
  float timerBuffInmortal = 0;
  final float segundosBuff = 5;
  float timerReanudacion = 0;
  final float segundosReanudacion = 3;
  //manager
  private AssetManager manager;


  public GameView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
    manager = new AssetManager();
    cargarMonedas();

    if (estado == EstadoJuego.INICIANDO) {
      cargarSkin();
      switch ((int)colorBoli){
        case 0:
          boli = new Boli((Texture) game.getManager().get("characters/boli_morado.png"), 200, 600);
          break;
        case 1:
          boli = new Boli((Texture) game.getManager().get("characters/boliVerde.png"), 200, 600);
          break;
        case 2:
          boli = new Boli((Texture) game.getManager().get("characters/boliAzul.png"), 200, 600);
          break;
        case 3:
          boli = new Boli((Texture) game.getManager().get("characters/boliRoja.png"), 200, 600);
          break;
      }
      gameStage = new Stage(super.viewport);
      gameText = new GameText("fuentes/exoFont.fnt");
      texturaFondo = game.getManager().get("mapas/NivelUno.png");

      initAudio();
      initMaps();
      initHUD();
      initShields();
    }

    Gdx.input.setInputProcessor(escenaHUD);

  }

  private void cargarSkin() {
    Preferences prefs = Gdx.app.getPreferences("elegir");
    colorBoli = prefs.getFloat("SKIN", 0);

  }

  private void initHUD() {
    camaraHUD = new OrthographicCamera(ANCHO_PANTALLA, ALTO_PANTALLA);
    camaraHUD.position.set(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2, 0);
    camaraHUD.update();
    vistaHUD = new StretchViewport(ANCHO_PANTALLA, ALTO_PANTALLA, camaraHUD);
    escenaHUD = new Stage(vistaHUD);


    //Creamos el botón de pausa (configuración va dentro de pausa).
    ImageButton btnGPause = new GameButton("buttons/btnPause.png");
    btnGPause.setPosition(ANCHO_PANTALLA * 0.95f, ALTO_PANTALLA * 0.90f, Align.center);
    //Acción botón
    final Screen gameView = this;
    btnGPause.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        timerReanudacion = 0;
        estado = EstadoJuego.REANUDANDO;
        musicaFondo.pause();
        game.setScreen(new PauseView(game, gameView));
      }
    });
    ImageButton bntSalto = new GameButton("buttons/boton_128.png");
    bntSalto.setPosition(ANCHO_PANTALLA * .85f, ALTO_PANTALLA * .2f, Align.center);
    bntSalto.addListener(new ClickListener() {

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

  private void initShields() {
    texturaEscudo = new Texture("characters/escudo.png");
    arrEscudos = new Array<>();
    for (int i = 1; i <= 3; i++) {
      Escudo escudo = new Escudo(texturaEscudo, i * 30, ALTO_PANTALLA * 0.88f);
      arrEscudos.add(escudo);
    }
  }

  private void initMaps() {
    cargarNivel();

    switch ((int)nivelEscogido){
      case 0:
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("mapas/platNivel1.tmx", TiledMap.class);
        manager.finishLoading();
        mapa = manager.get("mapas/platNivel1.tmx");
        break;
      case 1:
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("mapas/platNivel2.tmx", TiledMap.class);
        manager.finishLoading();
        mapa = manager.get("mapas/platNivel2.tmx");
        break;
    }

    rendererMapa = new OrthogonalTiledMapRenderer(mapa);

  }

  private void initAudio() {
    cargarNivel();
    //Cargamos las preferencias

    Preferences musica = Gdx.app.getPreferences("musica");
    switch ((int)nivelEscogido){
      case 0:
        playMusic = musica.getBoolean("MUSICA", true);

        manager.load("music/MusicaFondoNivel1.mp3", Music.class);
        manager.finishLoading();
        musicaFondo = manager.get("music/MusicaFondoNivel1.mp3");
        break;
      case 1:
        playMusic = musica.getBoolean("MUSICA", true);

        manager.load("music/MusicaFondoNivel2.mp3", Music.class);
        manager.finishLoading();
        musicaFondo = manager.get("music/MusicaFondoNivel2.mp3");
        break;
    }

    musicaFondo.setVolume(0.1f);
    musicaFondo.setLooping(true);
    if(playMusic){
      musicaFondo.play();
    } else {
      musicaFondo.pause();
    }
  }

  public void colisionPlataforma(){
    // Prueba si debe caer por llegar a un espacio vacío
      // Calcula la celda donde estaría después de moverlo
      int celdaX = (int) (boli.getX() / TAM_CELDA);
    int celdaY = (int) ((boli.getY() - boli.getV() / 60) / TAM_CELDA);

      // Recuperamos la celda en esta posición
      // La capa 0 es el fondo
      TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get(0);
      TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX, celdaY);
      TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX + 1, celdaY);
      // probar si la celda está ocupada

    if(esTrampaPicos(celdaAbajo) || esTrampaPicos(capa.getCell(celdaX+1,celdaY+1))){

      quitarEscudo = true;
    }

    if ( esBuffMultiplicador(capa.getCell(celdaX+1,celdaY+1)) ) {
      //Gdx.app.log("MUERTO", "F");      // Borrar Buff
      capa.setCell(celdaX+1,celdaY+1,null);
      boli.setEstadoBuff(EstadoBuff.BUFFDOBLEPUNTOS);

    }
    if ( esMoneda(capa.getCell(celdaX+1,celdaY+1)) ) {
      //Gdx.app.log("MUERTO", "F");      // Borrar Moneda
      capa.setCell(celdaX+1,celdaY+1,null);
      monedas++;
      guardarPreferencias();
    }
      if ((celdaAbajo == null && celdaDerecha == null && boli.getEstado() != EstadoBoli.SALTANDO) || (esBuffMultiplicador(celdaAbajo) && boli.getEstado() != EstadoBoli.SALTANDO)  || (esBuffMultiplicador(celdaDerecha) && boli.getEstado() != EstadoBoli.SALTANDO) || (esMoneda(celdaAbajo) && boli.getEstado() != EstadoBoli.SALTANDO) || (esMoneda(celdaDerecha) && boli.getEstado() != EstadoBoli.SALTANDO)){
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

  public boolean boliVivo(){
    //prueba
    if (boli.getY() + boli.sprite.getHeight() < 0) {
      camera.position.x = ANCHO_PANTALLA;
      musicaFondo.dispose();
      //game.setScreen(new DeathView(game, puntos));
      game.setScreen(new DeathView(game, puntos));
      return false;
    }
    return true;
  }


  private boolean esBuffMultiplicador(TiledMapTileLayer.Cell celda) {
    if (celda==null) {
      return false;
    }

    Object propiedad = celda.getTile().getProperties().get("tipo");
    return "BuffMultiplicador".equals(propiedad);
  }

  private boolean esTrampaPicos(TiledMapTileLayer.Cell celda) {
    if (celda==null) {
      return false;
    }

    Object propiedad = celda.getTile().getProperties().get("tipo");
    return "trampaPicos".equals(propiedad);
  }

  private boolean esMoneda(TiledMapTileLayer.Cell celda) {
    if (celda==null) {
      return false;
    }

    Object propiedad = celda.getTile().getProperties().get("tipo");
    return "moneda".equals(propiedad);
  }


  @Override
  public void render(float delta) {
    cleanScreen();
    colisionPlataforma();

    batch.begin();
    boliVivo();

    if(estado == EstadoJuego.JUGANDO){
      contadorFondo = contadorFondo - velocidadCamara;
      if(contadorFondo <= -texturaFondo.getWidth()){
        contadorFondo = 0;
      }
    }
    batch.draw(texturaFondo, contadorFondo,0);
    batch.draw(texturaFondo, texturaFondo.getWidth() + contadorFondo,0);

    batch.setProjectionMatrix(camera.combined);
    rendererMapa.setView(camera);
    rendererMapa.render();

    boli.render(batch);
    batch.end();

    actualizarTimer(delta);
    // COMPRUEBA SI BOLI ESTÁ VIVO (FALTARÍA AGREGAR ESTADO)
    boliVivo();

    // INICIANDO
    if (estado == EstadoJuego.INICIANDO && timerPausa <= 3){
      //boli.setEstadoBoli(EstadoBoli.QUIETO);
      batch.begin();
      gameText.mostrarMensaje(batch, "" + (int) (3 - timerPausa),
          ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2);
      batch.end();
    }


    if (estado == EstadoJuego.REANUDANDO){
      boli.sprite.setX(boli.getX());
      boli.sprite.setY(boli.getY());
      boli.setDX(0);
      boli.sprite.rotate(30);
      batch.begin();
      //Gdx.app.log("TIEMPO", "Tiempo: " + (int)(timerReanudacion/60));
      gameText.mostrarMensaje(batch, "" + (int) (3 - timerReanudacion/60),
              camera.position.x, camera.position.y);
      timerReanudacion++;
      batch.end();
      actualizarTimerReanudacion();
    } if (boli.getEstado() == EstadoBoli.CAYENDO){
      boli.sprite.setX(boli.getX());
      boli.sprite.setY(boli.getY());
      boli.setDX(4.5f);
    }


    if (estado == EstadoJuego.JUGANDO) {
      // DETIENE EL  PUNTAJE Y EL MOVIMIENTO DEL MAPA
      boli.setDX(4.5f);
      moverCamara();
      actualizar();
    }
    //Gdx.app.log("Boli X", String.valueOf(boli.getX()));
    if (alreadyWin()) {
      Preferences levelOneCompletion = Gdx.app.getPreferences("isLevelOneCompleted");
      levelOneCompletion.putBoolean("isLevelOneCompleted", true);
      levelOneCompletion.flush();
      camera.position.x = ANCHO_PANTALLA;
      musicaFondo.dispose();
      game.setScreen(new YouWinView(game));
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

  private boolean alreadyWin() {
    /* For second level
    return boli.getX >= 39818.0f; */

    return boli.getX() >= 49214.0f;
  }


  private void actualizarTimer(float delta) {
    timerPausa += delta;
    if (estado == EstadoJuego.INICIANDO && timerPausa >= 3) {
      estado = EstadoJuego.JUGANDO;
    }
  }

  private void actualizarTimerReanudacion() {
    if (timerReanudacion / 60 > segundosReanudacion) {
      estado = EstadoJuego.JUGANDO;
      musicaFondo.play();
      boli.setEstadoBoli(EstadoBoli.RODANDO);
    }
  }

  private void dibujarPuntaje() {
    int intPuntos = (int)puntos;
    gameText.mostrarMensaje(batch, "" + intPuntos, ANCHO_PANTALLA * 0.12f, ALTO_PANTALLA * 0.915f);
  }

  // LOS PUNTOS SE DETIENEN SI BOLI ESTÁ MUERTO
  private void actualizar() {
    if (boliVivo()){
      actualizarPuntos();
    }
    actualizarEscudo();
  }

  private void actualizarPuntos() {
    if(boli.getEstadoBuff() == EstadoBuff.BUFFDOBLEPUNTOS){
      timerBuffMultiplicador++;
      puntos+= 0.2f;
      if(timerBuffMultiplicador/60 > segundosBuff){
        timerBuffMultiplicador = 0;
        boli.setEstadoBuff(EstadoBuff.NORMAL);
      }
    }else{
      puntos+= 0.1f;
    }

  }


  private void actualizarEscudo() {
    if(boli.getEstadoBuff() == EstadoBuff.BUFFINMORTAL){
      timerBuffInmortal++;
      if(timerBuffInmortal/60 > segundosBuff){
        timerBuffInmortal = 0;
        boli.setEstadoBuff(EstadoBuff.NORMAL);
      }
    }else{
      for(int i = arrEscudos.size-1; i>= 0; i--){
        if(quitarEscudo){
          arrEscudos.removeIndex(i);
          boli.setEstadoBuff(EstadoBuff.BUFFINMORTAL);
          quitarEscudo = false;
          break;
        }

      }
    }
    if(arrEscudos.size == 0){
      Gdx.app.log("Perdiste", String.valueOf(boli.getX()));
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
  private void guardarPreferencias() {
    Preferences monedaPre = Gdx.app.getPreferences("monedas");
    monedaPre.putFloat("MONEDA", monedas);

    Preferences musica = Gdx.app.getPreferences("musica");
    musica.putBoolean("MUSICA", playMusic);

    monedaPre.flush();  // OBLIGATORIO
    musica.flush();
  }

  private void cargarMonedas() {
    Preferences monedaPre = Gdx.app.getPreferences("monedas");
    monedas = monedaPre.getFloat("MONEDA", 0);
  }

  private void cargarNivel() {
    Preferences nivel = Gdx.app.getPreferences("nivel");
    nivelEscogido = nivel.getFloat("NIVEL", 0);

  }


  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {
    musicaFondo.dispose();
    mapa.dispose();
    rendererMapa.dispose();
    texturaFondo.dispose();

  }


  public enum EstadoJuego {
    JUGANDO,
    PAUSANDO,
    INICIANDO,
    REANUDANDO,
    TERMINADO
  }

}
