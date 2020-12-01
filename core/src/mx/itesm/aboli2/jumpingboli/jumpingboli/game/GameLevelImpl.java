package mx.itesm.aboli2.jumpingboli.jumpingboli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.game.views.DeathView;
import mx.itesm.aboli2.jumpingboli.jumpingboli.GameText;

public class GameLevelImpl extends GameLevel {


  private String boliTexturePath = "characters/boli_morado.png";
  private String levelFontPath = "fuentes/exoFont.fnt";
  private String levelMapTmxPath = "mapas/NivelUno.tmx";
  private String audioPath = "music/MusicaFondoNivel1.mp3";
  private String backgroundPath = "mapas/NivelUno.png";
  private String jumpBtnPath = "buttons/boton_128.png";
  private String pauseBtnPath = "buttons/btnPause.png";


  public GameLevelImpl(GdXGame game) {
    super(game);
  }


  @Override
  protected void show_onStartOnly_() {
    // Todo: las lineas de asset loader van en la nueva clase.
    boli = new Boli((Texture) game.getManager().get(boliTexturePath), 200, 600);
  }

  @Override
  protected void onStartUp_initGameText() {
    // Todo: las lineas de asset loader van en la nueva clase.
    gameText = new GameText(levelFontPath);
  }


  @Override
  public void show_() {
    Gdx.input.setInputProcessor(stageHUD);
  }


  @Override
  protected void onStartUp_initMaps() {
    map = game.getManager().get(levelMapTmxPath);
    mapRenderer = new OrthogonalTiledMapRenderer(map);
  }

  @Override
  protected void onStartUp_initAudio() {
    backgroudMusic = game.getManager().get(audioPath);
    backgroudMusic.setVolume(0.1f);
    backgroudMusic.setLooping(true);
    backgroudMusic.play();
  }

  @Override
  protected void onStartUp_initBackground() {
    // Todo: las lineas de asset loader van en la nueva clase.
    backgroundTexture = game.getManager().get(backgroundPath);
  }

  @Override
  protected void _initButtons() {
    ImageButton btnGPause = getPauseBtn((Texture) game.getManager().get(pauseBtnPath));
    ImageButton bntSalto = getJumpBtn((Texture) game.getManager().get(jumpBtnPath));

    stageHUD.addActor(bntSalto);
    stageHUD.addActor(btnGPause);
  }

  @Override
  protected void render_(float delta) {
    batch.begin();

    if (!boliIsAlive()) {
      camera.position.x = ANCHO_PANTALLA;
      backgroudMusic.dispose();
      game.setScreen(new DeathView(game, score));
    }


    batch.draw(backgroundTexture, backgroundPositionInX, 0);
    batch.draw(backgroundTexture, backgroundTexture.getWidth() + backgroundPositionInX, 0);


    batch.setProjectionMatrix(camera.combined);
    mapRenderer.setView(camera);
    mapRenderer.render();

    boli.render(batch);
    batch.end();

    updateGameStatusIfTimerRunsOut(delta);


    if (gameIsStarting())
      DrawGetReadyCountDown();
    else if (gameIsResuming()) {
      boli.sprite.setX(boli.getX());
      boli.sprite.setY(boli.getY());

      boli.setDX(0);
      boli.sprite.rotate(30);
      drawResumeCountDown();
      startGameAgainIfTimerReanudacionRunsOut();
    }

    if (boli.getEstado() == EstadoBoli.CAYENDO) {

      boli.sprite.setX(boli.getX());
      boli.sprite.setY(boli.getY());
      boli.setDX(4.5f);
    }


    if (gameStatus == GameStatus.JUGANDO) {

      backgroundPositionInX = backgroundPositionInX - velocidadCamara;
      // Checar si puedo invertir!
      if (backgroundPositionInX <= -backgroundTexture.getWidth()) {
        backgroundPositionInX = 0;
      }

      boli.setDX(4.5f);
      moveCamera();
      updateScore();
      updateShields();
    }


    levelStage.draw();
    batch.setProjectionMatrix(cameraHUD.combined);
    batch.begin();
    drawShields();
    drawScore();
    batch.end();
    stageHUD.draw();
  }

  @Override
  public void dispose() {
    // Todo: Hacer los dispose en el asset manager
  }
}
