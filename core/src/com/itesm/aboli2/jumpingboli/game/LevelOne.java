package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;

public class LevelOne extends GameLevel {


  private Stage stageHUD;
  private OrthographicCamera cameraHUD;
  private Viewport viewHUD;


  public LevelOne(GdXGame game) {
    super(game);
  }

  @Override
  protected void show_onStartOnly_() {
    boli = new Boli(new Texture("characters/boli_morado.png"), 200, 600);
    initHUD();
    initShields();
  }

  @Override
  protected void onStartUp_initGameText() {
    gameText = new GameText("fuentes/exoFont.fnt");
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


  private void initShields() {
    shieldTexture = new Texture("characters/escudo.png");
    shieldArray = new Array<>();
    for (int i = 1; i <= 3; i++)
      shieldArray.add(new Escudo(shieldTexture, i * 30, ALTO_PANTALLA * 0.88f));
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

  private boolean esBuffMultiplicador(TiledMapTileLayer.Cell celda) {
    if (celda == null)
      return false;
    Object propiedad = celda.getTile().getProperties().get("tipo");
    return "BuffMultiplicador".equals(propiedad);
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

  private void drawResumeCountDown() {
    batch.begin();
    gameText.mostrarMensaje(batch, "" + (int) (3 - timerReanudacion / 60),
        camera.position.x, camera.position.y);
    timerReanudacion++;

    batch.end();
  }

  private boolean gameIsResuming() {
    return gameStatus == GameStatus.REANUDANDO;
  }

  private void DrawGetReadyCountDown() {
    batch.begin();
    gameText.mostrarMensaje(batch, "" + (int) (3 - timerPausa),
        ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2);
    batch.end();
  }

  private boolean gameIsStarting() {
    return gameStatus == GameStatus.INICIANDO && timerPausa <= 3;
  }


  private boolean boliIsAlive() {
    return (boli.getY() + boli.sprite.getHeight() > 0);
  }

  private void updateGameStatusIfTimerRunsOut(float delta) {
    timerPausa += delta;
    if (gameStatus == GameStatus.INICIANDO && timerPausa >= 3) {
      gameStatus = GameStatus.JUGANDO;
    }
  }

  private void startGameAgainIfTimerReanudacionRunsOut() {
    if (timerReanudacion / 60 > segundosReanudación) {
      gameStatus = GameStatus.JUGANDO;
      backgroudMusic.play();
      boli.setEstadoBoli(EstadoBoli.RODANDO);
    }
  }

  private void updateScore() {
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

  private void updateShields() {
    for (int i = shieldArray.size - 1; i >= 0; i--) {
      if (boli.getY() < 0) {
        shieldArray.removeIndex(i);
        break;
      }
    }
  }

}
