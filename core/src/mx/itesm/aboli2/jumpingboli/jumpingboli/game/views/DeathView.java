package mx.itesm.aboli2.jumpingboli.jumpingboli.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import mx.itesm.aboli2.jumpingboli.jumpingboli.GameText;
import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantalla;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantallas;
import mx.itesm.aboli2.jumpingboli.jumpingboli.button.ButtonFactory;
import mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView;

public class DeathView extends Pantalla {


  private Stage deathStage;
  private mx.itesm.aboli2.jumpingboli.jumpingboli.GameText gameText;
  private float puntos;
  private Texture texturaFondoMuerte;

  public DeathView(mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame game) {
    super(game);
  }

  public DeathView(GdXGame game, float puntos) {
    super(game);
    this.puntos = puntos;
  }

  @Override
  public void show() {

    // Bloquea la tecla back en el celular
    Gdx.input.setCatchKey(Input.Keys.BACK, true);

    deathStage = new Stage(super.viewport);
    texturaFondoMuerte = new Texture("fondos/fondoMuerte.png");
    gameText = new GameText("fuentes/exoFont.fnt");
    ImageButton backBtn = game.buttonFactory.returnToMenuBtn();
    deathStage.addActor(ButtonFactory.getPlayBtn(game, new LoadingView(game, Pantallas.GAME)));
    deathStage.addActor(backBtn);
    Gdx.input.setInputProcessor(deathStage);

  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(texturaFondoMuerte,0,0);
    //gameText.mostrarMensaje(batch, "You are Dead", ANCHO_PANTALLA * .5f, ALTO_PANTALLA * .8f, 200f);
    gameText.mostrarMensaje(batch, "Score:" + " " + (int) puntos + " pts", ANCHO_PANTALLA * .500f, ALTO_PANTALLA * .665f, 300f);
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
