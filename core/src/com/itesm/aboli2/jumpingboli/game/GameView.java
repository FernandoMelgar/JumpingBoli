package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
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


  public GameView(GdXGame game) {
    super(game);
  }


  @Override
  public void show() {
    gameStage = new Stage(super.viewport);
    gameStage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));
    boli = new Boli(new Texture("characters/Boli_50.png"), 200,200);


    ImageButton btnAjustes = new GameButton("buttons/ajustes.png");
    btnAjustes.setPosition(ANCHO_PANTALLA - btnAjustes.getWidth() * 1.5f, ALTO_PANTALLA - btnAjustes.getHeight() * 1.5f);
    gameStage.addActor(btnAjustes);

    Gdx.input.setInputProcessor(new ProcesadorEntrada());

  }

  @Override
  public void render(float delta) {
    cleanScreen();
    moverCamara();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(new Texture("fondos/Nivel1.jpeg"), 0, 0);
    boli.render(batch);
    batch.end();
    gameStage.draw();
  }

  private void moverCamara() {

    camera.position.x = camera.position.x + speedCamera;
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

      if (v.x<=ANCHO_PANTALLA/2 && boli.getEstado() == EstadoBoli.RODANDO) {
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
