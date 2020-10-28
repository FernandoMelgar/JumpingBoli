package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class DeathView extends Pantalla {


  private Stage deathStage;
  private GameText gameText;
  private float puntos;

  public DeathView(GdXGame game) {
    super(game);
  }

  public DeathView(GdXGame game, float puntos) {
    super(game);
    this.puntos = puntos;
  }

  @Override
  public void show() {
    deathStage = new Stage(super.viewport);
    gameText = new GameText("fuentes/exoFont.fnt");
    deathStage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));
    deathStage.addActor(ButtonFactory.getPlayBtn(game, new GameView(game)));
    Gdx.input.setInputProcessor(deathStage);

  }

  @Override
  public void render(float delta) {
    cleanScreen();
    // rgb(180,19,1)
    paintScreen(180 / 255f, 19 / 255f, 1 / 255f);
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    gameText.mostrarMensaje(batch, "You are Dead", ANCHO_PANTALLA * .5f, ALTO_PANTALLA * .8f, 200f);
    gameText.mostrarMensaje(batch, "Score:" + " " + puntos + " pts", ANCHO_PANTALLA * .5f, ALTO_PANTALLA * .7f, 200f);
    batch.end();
    deathStage.draw();
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

}
