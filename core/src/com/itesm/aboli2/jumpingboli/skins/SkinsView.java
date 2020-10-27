package com.itesm.aboli2.jumpingboli.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class SkinsView extends Pantalla {

  //Escena
  Stage skinsStage;

  //Fondo
  private Texture texturaFondo;

  //Texturas Boli


  //Textura plataforma
  private Texture texturaPlataforma;

  //Texto
  private GameText gameText;
  private float puntos;

  public SkinsView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
   skinsStage = new Stage(super.viewport);
   texturaFondo = new Texture("fondos/fondoExtra.png");
   texturaPlataforma = new Texture("characters/plataformaFondo.png");
   createSkinsView();
   createText();
   Gdx.input.setInputProcessor(skinsStage);
  }

  private void createText() {
    gameText = new GameText("fuentes/exoFont.fnt");
  }

  private void createSkinsView() {
    crearBtnBack();
    crearBtnSelect();
    //Checar preferencias donde guardemos si el jugador ya desbloqueó los niveles para ver si se crean o no.
    crearBtnUnlock();
    crearBtnUnlock2();
    crearBtnUnlock3();
  }

  private void crearBtnUnlock3() {
    ImageButton btnUnlock = new GameButton("buttons/btnUnlock3.png", "buttons/btnUnlockPicado3.png");
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * 0.35f, Align.center);
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnUnlock2() {
    ImageButton bntUnlock = new GameButton("buttons/btnUnlock2.png", "buttons/btnUnlockPicado2.png");
    bntUnlock.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * 0.5f, Align.center);
    skinsStage.addActor(bntUnlock);
  }

  private void crearBtnUnlock() {
    ImageButton btnUnlock = new GameButton("buttons/btnUnlock.png", "buttons/btnUnlockPicado.png");
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * .65f, Align.center);
    skinsStage.addActor(btnUnlock);

  }

  private void crearBtnSelect() {
    ImageButton btnSelect = new GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
    btnSelect.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * 0.8f, Align.center);
    skinsStage.addActor(btnSelect);
  }

  private void crearBtnBack() {
    skinsStage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));

  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(texturaFondo, 0, 0);
    batch.draw(texturaPlataforma, ANCHO_PANTALLA*0.15f, ALTO_PANTALLA/6);
    //dibujarTexto();
    batch.end();

    skinsStage.draw();
  }

  private void dibujarTexto() {
    gameText.mostrarMensaje(batch, "SKINS", ANCHO_PANTALLA / 2, ALTO_PANTALLA * 0.9f);
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
