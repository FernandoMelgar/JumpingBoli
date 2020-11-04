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

public class GameLevelImpl extends GameLevel {


  private String boliTexturePath;
  private String levelFontPath;
  private String levelMapTmxPath;
  private String audioPath;
  private String backgroundPath;

  private GameLevelImpl(Builder builder) {
    super(builder.gdxGame);
    boliTexturePath = builder.boliTexturePath;
    levelFontPath = builder.levelFontPath;
    levelMapTmxPath = builder.levelMapTmxPath;
    audioPath = builder.audioPath;
    backgroundPath = builder.backgroundPath;

  }

  public static class Builder {

    private final String backgroundPath;
    private final String levelMapTmxPath;
    private final GdXGame gdxGame;
    private String boliTexturePath = "characters/boli_morado.png";
    ;
    private String levelFontPath = "fuentes/exoFont.fnt";
    private String audioPath = "music/MusicaFondoNivel1.mp3";

    public Builder(GdXGame gdxGame, String backgroundPath, String levelMapTmxPath) {
      this.gdxGame = gdxGame;
      this.backgroundPath = backgroundPath;
      this.levelMapTmxPath = levelMapTmxPath;
    }

    public Builder boliTexurePath(String texture) {
      this.boliTexturePath = texture;
      return this;
    }

    public Builder levelFont(String fontPath) {
      this.levelFontPath = fontPath;
      return this;
    }

    public Builder audioPath(String audioPath) {
      this.audioPath = audioPath;
      return this;
    }

    public GameLevelImpl build() {
      return new GameLevelImpl(this);
    }

  }


  @Override
  protected void show_onStartOnly_() {
    boli = new Boli(new Texture(boliTexturePath), 200, 600);
  }

  @Override
  protected void onStartUp_initGameText() {
    gameText = new GameText(levelFontPath);
  }


  @Override
  public void show_() {
    Gdx.input.setInputProcessor(stageHUD);
  }


  @Override
  protected void onStartUp_initMaps() {
    assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    assetManager.load(levelMapTmxPath, TiledMap.class);
    assetManager.finishLoading();
    map = assetManager.get(levelMapTmxPath);
    mapRenderer = new OrthogonalTiledMapRenderer(map);
  }

  @Override
  protected void onStartUp_initAudio() {
    assetManager.load(audioPath, Music.class);
    assetManager.finishLoading();
    backgroudMusic = assetManager.get(audioPath);
    backgroudMusic.setVolume(0.1f);
    backgroudMusic.setLooping(true);
    backgroudMusic.play();
  }

  @Override
  protected void onStartUp_initBackground() {
    backgroundTexture = new Texture(backgroundPath);
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
