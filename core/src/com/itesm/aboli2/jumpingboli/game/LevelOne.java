package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;

public class LevelOne extends GameLevel {


  public LevelOne(GdXGame game) {
    super(game);
  }

  @Override
  protected void show_onStartOnly_() {
    boli = new Boli(new Texture("characters/boli_morado.png"), 200, 600);
  }

  @Override
  protected void onStartUp_initGameText() {
    gameText = new GameText("fuentes/exoFont.fnt");
  }


  @Override
  public void show_() {
    Gdx.input.setInputProcessor(stageHUD);
  }


  @Override
  protected void onStartUp_initMaps() {
    assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    assetManager.load("mapas/platNivel1.tmx", TiledMap.class);
    assetManager.finishLoading();
    map = assetManager.get("mapas/platNivel1.tmx");
    mapRenderer = new OrthogonalTiledMapRenderer(map);
  }

  @Override
  protected void onStartUp_initAudio() {
    assetManager.load("music/MusicaFondoNivel1.mp3", Music.class);
    assetManager.finishLoading();
    backgroudMusic = assetManager.get("music/MusicaFondoNivel1.mp3");
    backgroudMusic.setVolume(0.1f);
    backgroudMusic.setLooping(true);
    backgroudMusic.play();
  }

  @Override
  protected void onStartUp_initBackground() {
    backgroundTexture = new Texture("mapas/NivelUno.png");
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
}
