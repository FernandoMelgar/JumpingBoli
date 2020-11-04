package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
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
    show_onStartOnly_();

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

  protected abstract void render_checkForCollisions();

  protected abstract void render_(float delta);

  public enum GameStatus {
    JUGANDO,
    PAUSANDO,
    INICIANDO,
    REANUDANDO,
    TERMINADO
  }
}
