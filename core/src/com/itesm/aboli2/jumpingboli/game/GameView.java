package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.itesm.aboli2.jumpingboli.Boli;
import com.itesm.aboli2.jumpingboli.EstadoBoli;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.menu.MenuView;


public class GameView extends Pantalla {

  private Stage gameStage;

  private int speedCamera = 2;

  //Boli
  private Boli boli;

  //Mapa
  private TiledMap mapa;
  private OrthogonalTiledMapRenderer rendererMapa;

  // musica
  private Music musicaFondo;


  public GameView(GdXGame game) {
    super(game);
  }


  @Override
  public void show() {
    crearAudio();
    crearMapa();
    gameStage = new Stage(super.viewport);
    gameStage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));
    boli = new Boli(new Texture("characters/Boli_50.png"), 200,200);


    ImageButton btnAjustes = new GameButton("buttons/ajustes.png");
    btnAjustes.setPosition(ANCHO_PANTALLA - btnAjustes.getWidth() * 1.5f, ALTO_PANTALLA - btnAjustes.getHeight() * 1.5f);
    gameStage.addActor(btnAjustes);

    Gdx.input.setInputProcessor(new ProcesadorEntrada());

  }

  private void crearMapa() {
    //Se crea el asset manager para manejar el mapa.
    AssetManager manager = new AssetManager();
    manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    manager.load("mapas/NivelUno.tmx", TiledMap.class);
    manager.finishLoading();
    mapa = manager.get("mapas/NivelUno.tmx");
    rendererMapa = new OrthogonalTiledMapRenderer(mapa);

  }

  private void crearAudio() {
    AssetManager manager = new AssetManager();
    manager.load("music/MusicaFondoNivel1.mp3", Music.class);
    manager.finishLoading(); //Espera
    musicaFondo = manager.get("music/MusicaFondoNivel1.mp3");
    musicaFondo.setVolume(0.1f);
    musicaFondo.setLooping(true);
    musicaFondo.play();
  }


  @Override
  public void render(float delta) {
    cleanScreen();
    moverCamara();
    batch.setProjectionMatrix(camera.combined);

    rendererMapa.setView(camera);
    rendererMapa.render();

    batch.begin();
    boli.render(batch);
    batch.end();

    gameStage.draw();
  }

  private void moverCamara() {

    camera.position.x = camera.position.x + speedCamera;
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

  private class  ProcesadorEntrada implements InputProcessor {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      Vector3 v = new Vector3(screenX, screenY, 0);
      camera.unproject(v);

      if (v.x<=ANCHO_PANTALLA/2 + camera.position.x && boli.getEstado() == EstadoBoli.RODANDO) {
        boli.saltar();
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

}
