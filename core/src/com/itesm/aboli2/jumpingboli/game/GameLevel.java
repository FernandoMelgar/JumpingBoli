package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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
import com.itesm.aboli2.jumpingboli.Pause.PauseView;
import com.itesm.aboli2.jumpingboli.button.GameButton;

public abstract class GameLevel extends Pantalla {


  protected float velocidadCamara = 1;
  protected final int TAM_CELDA = 32;
  protected float backgroundPositionInX = 0;
  protected float score;

  protected Texture backgroundTexture;
  protected Stage levelStage;
  protected AssetManager assetManager;
  protected TiledMap map;
  protected OrthogonalTiledMapRenderer mapRenderer;
  protected Music backgroudMusic;
  protected GameText gameText;

  protected Boli boli;

  public GameStatus gameStatus = GameStatus.INICIANDO;

  protected Texture shieldTexture;
  protected Array<Escudo> shieldArray;


  //TIMER
  protected float timerPausa;
  protected float timerBuffMultiplicador = 0;
  protected final float segundosBuff = 5;
  protected float timerReanudacion = 0;
  protected final float segundosReanudación = 3;


  protected Stage stageHUD;
  protected OrthographicCamera cameraHUD;
  protected Viewport viewHUD;


  public GameLevel(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
    if (gameStatus == GameStatus.INICIANDO)
      show_onlyOnStartUp();
    show_();
  }


  public abstract void show_();

  protected void show_onlyOnStartUp() {
    levelStage = new Stage(super.viewport);
    assetManager = new AssetManager();
    onStartUp_initBackground();
    onStartUp_initGameText();
    onStartUp_initAudio();
    onStartUp_initMaps();
    initHUD();
    initShields();
    show_onStartOnly_();

  }

  protected void initShields() {
    shieldTexture = new Texture("characters/escudo.png");
    shieldArray = new Array<>();
    for (int i = 1; i <= 3; i++)
      shieldArray.add(new Escudo(shieldTexture, i * 30, ALTO_PANTALLA * 0.88f));
  }

  private void initHUD() {
    cameraHUD = new OrthographicCamera(ANCHO_PANTALLA, ALTO_PANTALLA);
    cameraHUD.position.set(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2, 0);
    cameraHUD.update();
    viewHUD = new StretchViewport(ANCHO_PANTALLA, ALTO_PANTALLA, cameraHUD);
    stageHUD = new Stage(viewHUD);

    ImageButton btnGPause = getPauseBtn();
    ImageButton bntSalto = getJumpBtn();

    stageHUD.addActor(bntSalto);
    stageHUD.addActor(btnGPause);
  }


  protected abstract void onStartUp_initBackground();

  protected abstract void onStartUp_initGameText();

  protected abstract void onStartUp_initAudio();

  protected abstract void onStartUp_initMaps();

  protected abstract void show_onStartOnly_();


  @Override
  public void render(float delta) {
    cleanScreen();
    render_checkForCollisions();
    render_(delta);
  }

  protected void moveCamera() {
    camera.position.x = camera.position.x + boli.getDX();
    camera.update();
  }

  protected void drawShields() {
    if (shieldArray == null)
      return;
    for (Escudo escudo : shieldArray)
      escudo.render(batch);
  }

  protected void drawScore() {
    int intScore = (int) score;
    gameText.mostrarMensaje(batch, "" + intScore, ANCHO_PANTALLA * 0.12f, ALTO_PANTALLA * 0.915f);
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


  protected ImageButton getPauseBtn() {
    ImageButton btnGPause = new GameButton("buttons/btnPause.png");
    btnGPause.setPosition(ANCHO_PANTALLA * 0.95f, ALTO_PANTALLA * 0.90f, Align.center);

    final Screen currentGameView = this;
    btnGPause.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);

        timerReanudacion = 0;
        gameStatus = GameStatus.REANUDANDO;
        backgroudMusic.pause();
        game.setScreen(new PauseView(game, currentGameView));
      }
    });

    return btnGPause;
  }

  protected ImageButton getJumpBtn() {

    ImageButton bntSalto = new GameButton("buttons/boton_128.png");
    bntSalto.setPosition(ANCHO_PANTALLA * .9f, ALTO_PANTALLA * .1f, Align.center);
    bntSalto.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (boli.getEstado() == EstadoBoli.RODANDO) {
          boli.setyBase(boli.getY());
          boli.saltar();
        }
      }
    });
    return bntSalto;
  }

  protected void updateShields() {
    if (shieldArray == null)
      return;
    for (int i = shieldArray.size - 1; i >= 0; i--) {
      if (boli.getY() < 0) {
        shieldArray.removeIndex(i);
        break;
      }
    }
  }

  protected void updateScore() {
    if (boli.getEstadoBuff() == EstadoBuff.BUFFDOBLEPUNTOS) {

      timerBuffMultiplicador++;
      score += 0.2f;

      if (timerBuffMultiplicador / 60 > segundosBuff) {
        timerBuffMultiplicador = 0;
        boli.setEstadoBuff(EstadoBuff.NORMAL);
      }
    } else {
      score += 0.1f;
    }

  }

  protected boolean esBuffMultiplicador(TiledMapTileLayer.Cell celda) {
    if (celda == null)
      return false;
    Object propiedad = celda.getTile().getProperties().get("tipo");
    return "BuffMultiplicador".equals(propiedad);
  }

  protected boolean boliIsAlive() {
    return (boli.getY() + boli.sprite.getHeight() > 0);
  }

  protected void drawResumeCountDown() {
    batch.begin();
    gameText.mostrarMensaje(batch, "" + (int) (3 - timerReanudacion / 60),
        camera.position.x, camera.position.y);
    timerReanudacion++;

    batch.end();
  }

  protected boolean gameIsResuming() {
    return gameStatus == GameStatus.REANUDANDO;
  }

  protected void DrawGetReadyCountDown() {
    batch.begin();
    gameText.mostrarMensaje(batch, "" + (int) (3 - timerPausa),
        ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2);
    batch.end();
  }

  protected boolean gameIsStarting() {
    return gameStatus == GameStatus.INICIANDO && timerPausa <= 3;
  }

  protected void updateGameStatusIfTimerRunsOut(float delta) {
    timerPausa += delta;
    if (gameStatus == GameStatus.INICIANDO && timerPausa >= 3) {
      gameStatus = GameStatus.JUGANDO;
    }
  }

  protected void startGameAgainIfTimerReanudacionRunsOut() {
    if (timerReanudacion / 60 > segundosReanudación) {
      gameStatus = GameStatus.JUGANDO;
      backgroudMusic.play();
      boli.setEstadoBoli(EstadoBoli.RODANDO);
    }
  }


  protected void render_checkForCollisions() {
    int celdaX = (int) (boli.getX() / TAM_CELDA);
    int celdaY = (int) ((boli.getY() + 1.5 * boli.DY) / TAM_CELDA);


    TiledMapTileLayer capa = (TiledMapTileLayer) map.getLayers().get(0);
    TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX, celdaY);
    TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX + 1, celdaY);


    if (esBuffMultiplicador(capa.getCell(celdaX + 1, celdaY + 1))) {
      Gdx.app.log("MUERTO", "F");      // Borrar esta estrella y contabilizar
      capa.setCell(celdaX + 1, celdaY + 1, null);
      boli.setEstadoBuff(EstadoBuff.BUFFDOBLEPUNTOS);
    }
    if ((celdaAbajo == null && celdaDerecha == null && boli.getEstado() != EstadoBoli.SALTANDO) || (esBuffMultiplicador(celdaAbajo) && boli.getEstado() != EstadoBoli.SALTANDO) || (esBuffMultiplicador(celdaDerecha) && boli.getEstado() != EstadoBoli.SALTANDO)) {
      if (boli.getEstado() != EstadoBoli.CAYENDO) {
        boli.setyBase(boli.getY());
        boli.cayendo();
      }
    } else if (boli.getEstado() != EstadoBoli.SALTANDO) {
      // Dejarlo sobre la celda que lo detiene
      boli.setPosicion(boli.getX(), (celdaY + 1) * TAM_CELDA);
      boli.setEstadoBoli(EstadoBoli.RODANDO);
    }

  }

  protected abstract void render_(float delta);

  public enum GameStatus {
    JUGANDO,
    PAUSANDO,
    INICIANDO,
    REANUDANDO,
    TERMINADO
  }
}
