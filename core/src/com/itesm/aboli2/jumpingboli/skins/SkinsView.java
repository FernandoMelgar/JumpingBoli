package com.itesm.aboli2.jumpingboli.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
  private Seleccion boliSeleccionada = Seleccion.MORADA;
  private String texturaBoliElegida;
  private float texturaElegida = 0;

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

   crearTexturas();
   cargarSkin();
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
    cargarSkin();
    switch ((int)texturaElegida){
      case 0:
        boliCentral = new Boli((Texture) game.getManager().get("characters/boli_morado.png"), ANCHO_PANTALLA*0.17f, ALTO_PANTALLA*0.21f);
        break;
      case 1:
        boliCentral = new Boli((Texture) game.getManager().get("characters/boliVerde.png"), ANCHO_PANTALLA*0.17f, ALTO_PANTALLA*0.21f);
        break;
      case 2:
        boliCentral = new Boli((Texture) game.getManager().get("characters/boliAzul.png"), ANCHO_PANTALLA*0.17f, ALTO_PANTALLA*0.21f);
        break;
      case 3:
        boliCentral = new Boli((Texture) game.getManager().get("characters/boliRoja.png"), ANCHO_PANTALLA*0.17f, ALTO_PANTALLA*0.21f);
        break;
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
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);

        texturaBoliElegida = "characters/boliAzul.png";
        texturaElegida = 2;
        guardarPreferencias();
        boliCentral.setTextura(new Texture(texturaBoliElegida));
        boliSeleccionada = Seleccion.VERDE;

      }
    });
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnUnlock2() {
    ImageButton bntUnlock = new GameButton("buttons/btnUnlock2.png", "buttons/btnUnlockPicado2.png");
    bntUnlock.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * 0.4f, Align.center);
    bntUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);

        texturaBoliElegida = "characters/boliRoja.png";
        texturaElegida = 3;
        guardarPreferencias();
        boliCentral.setTextura(new Texture(texturaBoliElegida));
        boliSeleccionada = Seleccion.VERDE;

      }
    });
    skinsStage.addActor(bntUnlock);
  }

  private void crearBtnUnlock() {
    ImageButton btnUnlock = new GameButton("buttons/btnUnlock.png", "buttons/btnUnlockPicado.png");
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.69f, ALTO_PANTALLA * .55f, Align.center);
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);

        texturaBoliElegida = "characters/boliVerde.png";
        texturaElegida = 1;
        guardarPreferencias();
        boliCentral.setTextura(new Texture(texturaBoliElegida));
        boliSeleccionada = Seleccion.VERDE;

      }
    });
    skinsStage.addActor(btnUnlock);

  }

  private void crearBtnSelect() {
    ImageButton btnSelect = new GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
    btnSelect.setPosition(ANCHO_PANTALLA * 0.5f, ALTO_PANTALLA * 0.7f, Align.center);
    btnSelect.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);

        texturaBoliElegida = "characters/boli_morado.png";
        texturaElegida = 0;
        guardarPreferencias();
        boliCentral.setTextura(new Texture(texturaBoliElegida));
        boliSeleccionada = Seleccion.VERDE;

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

  private void guardarPreferencias() {
    Preferences prefs = Gdx.app.getPreferences("elegir");
    prefs.putFloat("SKIN", texturaElegida);
    prefs.flush();  // OBLIGATORIO
  }

  private void cargarSkin() {
    Preferences prefs = Gdx.app.getPreferences("elegir");
    texturaElegida = prefs.getFloat("SKIN", 0);

  }
}
