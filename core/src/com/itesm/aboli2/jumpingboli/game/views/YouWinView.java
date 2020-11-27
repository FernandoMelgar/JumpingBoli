package com.itesm.aboli2.jumpingboli.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;

public class YouWinView extends Pantalla {


  private Stage youWinStage;
  private GameText gameText;
  private float puntos;
  private Texture texturaFondoYouWin;

  public YouWinView(GdXGame game) {
    super(game);
  }

  public YouWinView(GdXGame game, float puntos) {
    super(game);
    this.puntos = puntos;
  }

  @Override
  public void show() {
    youWinStage = new Stage(super.viewport);
    texturaFondoYouWin = new Texture("fondos/fondoYouWin.png");
    gameText = new GameText("fuentes/exoFont.fnt");
    youWinStage.addActor(ButtonFactory.toLevelSelectView(game));
    Gdx.input.setInputProcessor(youWinStage);
    guardarPreferencias();

  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(texturaFondoYouWin, 0, 0);
    gameText.mostrarMensaje(batch, "Score:" + " " + (int) puntos + " pts", ANCHO_PANTALLA * .535f, ALTO_PANTALLA * .665f, 300f);
    batch.end();
    youWinStage.draw();
  }

  private void guardarPreferencias() {
    Preferences prefs = Gdx.app.getPreferences("isLevelOneCompleted");
    prefs.putBoolean("ISLevelOneCompleted", true);

    prefs.flush();  // OBLIGATORIO
  }
  @Override
  public void dispose() {
    batch.dispose();
  }

}
