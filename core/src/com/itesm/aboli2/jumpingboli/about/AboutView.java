package com.itesm.aboli2.jumpingboli.about;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.menu.MenuView;


public class AboutView extends Pantalla {

  //Fondo
  private Texture texturaFondo;

  //Texto
  private GameText gameText;
  private float puntos;

  //HUD
  private Stage escenaHUD;
  private OrthographicCamera camaraHUD;
  private Viewport vistaHUD;


  private Stage aboutStage;
  BitmapFont font = new BitmapFont();

  public AboutView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
    aboutStage = new Stage(super.viewport);
    texturaFondo = new Texture("fondos/fondoExtra.png");
    createText();
    crearButtonLayer();
    Gdx.input.setInputProcessor(escenaHUD);
  }

  private void createText() {
    gameText = new GameText("fuentes/exoFont.fnt");
  }

  private void crearButtonLayer() {
    camaraHUD = new OrthographicCamera(ANCHO_PANTALLA, ALTO_PANTALLA);
    camaraHUD.position.set(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2, 0);
    camaraHUD.update();
    vistaHUD = new StretchViewport(ANCHO_PANTALLA, ALTO_PANTALLA, camaraHUD);

    escenaHUD = new Stage(vistaHUD);

    //Creamos el botón back

    escenaHUD.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));

  }


  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(texturaFondo, 0, 0);
    dibujarTexto();
    batch.end();

    aboutStage.draw();
    escenaHUD.draw();

  }

  private void dibujarTexto() {
    gameText.mostrarMensaje(batch, "About us:", ANCHO_PANTALLA / 2, ALTO_PANTALLA * 0.9f);
    gameText.mostrarMensaje(batch, "Creators", ANCHO_PANTALLA / 2, ALTO_PANTALLA * 0.85f);
    gameText.mostrarMensaje(batch, "Fernando Manuel Melgar Fuentes - A01748354\n" +
        "Alex Fernando Leyva Martinez - A01747078\n" +
        "Arturo Márquez Olivar - A01376086\n" +
        "Claudio Mayoral Garcia - A01747749", ANCHO_PANTALLA / 2, ALTO_PANTALLA * 0.8f);


  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {
    texturaFondo.dispose();
    batch.dispose();
  }
}
