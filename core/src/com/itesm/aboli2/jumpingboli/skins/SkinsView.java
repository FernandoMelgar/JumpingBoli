package com.itesm.aboli2.jumpingboli.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.game.Boli;
import com.itesm.aboli2.jumpingboli.game.EstadoBoli;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class SkinsView extends Pantalla {

  //Escena
  Stage skinsStage;

  //Fondo
  private Texture texturaFondo;

  //Texturas Boli
  private Boli boliCentral;
  private Boli boliMorada;
  private Boli boliVerde;
  private Boli boliRoja;
  private Boli boliAzul;
  private Texture texturaBoliMorada;

  //Boli seleccionada
  private Seleccion boliSeleccionada;

  //Textura plataforma
  private Texture texturaPlataforma;

  //Timer para el salto de Boli
  private float timerSalto;
  private float timerRegreso;

  //Texto
  private GameText gameText;
  private float puntos;

  public SkinsView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
   skinsStage = new Stage(super.viewport);
   boliSeleccionada = Seleccion.MORADA;
   crearTexturas();
   crearBolis();
   cambiarEstadosBolis();
   createSkinsView();
   createText();
   Gdx.input.setInputProcessor(skinsStage);
  }

  private void cambiarEstadosBolis() {
    boliCentral.setEstadoBoli(EstadoBoli.QUIETO);
    boliMorada.setEstadoBoli(EstadoBoli.QUIETO);
    boliVerde.setEstadoBoli(EstadoBoli.QUIETO);
    boliRoja.setEstadoBoli(EstadoBoli.QUIETO);
    boliAzul.setEstadoBoli(EstadoBoli.QUIETO);
  }

  private void crearBolis() {
    if(boliSeleccionada == Seleccion.MORADA){
      boliCentral = new Boli(new Texture("characters/boli_morado.png"), ANCHO_PANTALLA*0.17f, ALTO_PANTALLA*0.21f);
    } else if(boliSeleccionada == Seleccion.VERDE){
      boliCentral = new Boli(new Texture("characters/boli_morado.png"), ANCHO_PANTALLA*0.17f, ALTO_PANTALLA*0.21f);
    } else if(boliSeleccionada == Seleccion.AZUL){
      boliCentral = new Boli(new Texture("characters/boli_morado.png"), ANCHO_PANTALLA*0.17f, ALTO_PANTALLA*0.21f);
    } else if(boliSeleccionada == Seleccion.ROJA){
      boliCentral = new Boli(new Texture("characters/boli_morado.png"), ANCHO_PANTALLA*0.17f, ALTO_PANTALLA*0.21f);
    }
    boliMorada = new Boli(new Texture("characters/boli_morado.png"), ANCHO_PANTALLA*0.35f, ALTO_PANTALLA*0.67f);
    boliVerde = new Boli(new Texture("characters/boliVerde.png"), ANCHO_PANTALLA*0.35f, ALTO_PANTALLA*0.52f);
    boliRoja = new Boli(new Texture("characters/boliRoja.png"), ANCHO_PANTALLA*0.35f, ALTO_PANTALLA*0.37f);
    boliAzul = new Boli(new Texture("characters/boliAzul.png"), ANCHO_PANTALLA*0.35f, ALTO_PANTALLA*0.22f);
  }

  private void crearTexturas() {
    texturaFondo = new Texture("fondos/fondoExtra.png");
    texturaPlataforma = new Texture("characters/plataformaFondo.png");
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
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * 0.25f, Align.center);
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnUnlock2() {
    ImageButton bntUnlock = new GameButton("buttons/btnUnlock2.png", "buttons/btnUnlockPicado2.png");
    bntUnlock.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * 0.4f, Align.center);
    skinsStage.addActor(bntUnlock);
  }

  private void crearBtnUnlock() {
    ImageButton btnUnlock = new GameButton("buttons/btnUnlock.png", "buttons/btnUnlockPicado.png");
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * .55f, Align.center);
    skinsStage.addActor(btnUnlock);

  }

  private void crearBtnSelect() {
    ImageButton btnSelect = new GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
    btnSelect.setPosition(ANCHO_PANTALLA * 0.5f, ALTO_PANTALLA * 0.7f, Align.center);
    btnSelect.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        boliSeleccionada = Seleccion.MORADA;
      }
    });
    skinsStage.addActor(btnSelect);
  }

  private void crearBtnBack() {
    skinsStage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));
  }

  @Override
  public void render(float delta) {
    cleanScreen();
    hacerSaltarBoli(delta);
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(texturaFondo, 0, 0);
    batch.draw(texturaPlataforma, ANCHO_PANTALLA*0.15f, ALTO_PANTALLA/6);
    boliCentral.render(batch);
    boliMorada.render(batch);
    boliVerde.render(batch);
    boliRoja.render(batch);
    boliAzul.render(batch);
    batch.end();

    skinsStage.draw();
  }

  private void hacerSaltarBoli(float tiempo) {
    timerSalto += tiempo;
    if (timerSalto >= 10){
      boliCentral.setEstadoBoli(EstadoBoli.SALTANDO);
      boliCentral.saltar();
      timerSalto = 0;
    }
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
