package com.itesm.aboli2.jumpingboli.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
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

  //Desbloqueos de Boli
  private Boolean boliVerdeDesbloqueada;
  private Boolean boliRojaDesbloqueada;
  private Boolean boliAzulDesbloqueada;
  private String btnBoliDesbloquedo;
  private Array<Integer> boliDesbloqueada;

  //Boli seleccionada
  private String texturaBoliElegida;
  private float texturaElegida = 0;

  //Textura plataforma
  private Texture texturaPlataforma;

  //Timer para el salto de Boli
  private float timerSalto;
  private float timerRegreso;

  //Texto
  private GameText gameText;
  private float monedas;

  public SkinsView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
   skinsStage = new Stage(super.viewport);
   boliDesbloqueada = new Array<>(4);
   //reiniciarDesbloqueos();
   cargarDesbloqueos();
   revisarDesbloqueos();
   cargarMonedas();
   crearTexturas();
   cargarSkin();
   crearBolis();
   cambiarEstadosBolis();
   createSkinsView();
   createText();
   Gdx.input.setInputProcessor(skinsStage);
  }

  private void reiniciarDesbloqueos() {
    boliVerdeDesbloqueada = false;
    boliRojaDesbloqueada = false;
    boliAzulDesbloqueada = false;
  }

  private void cargarDesbloqueos() {
    Preferences unlockGreen = Gdx.app.getPreferences("desbloquearVerde");
    boliVerdeDesbloqueada = unlockGreen.getBoolean("desbloqueoVerde", false);

    Preferences unlockRed = Gdx.app.getPreferences("desbloquearRoja");
    boliRojaDesbloqueada = unlockRed.getBoolean("desbloqueoRoja", false);

    Preferences unlockBlue = Gdx.app.getPreferences("desbloquearAzul");
    boliAzulDesbloqueada = unlockBlue.getBoolean("desbloqueoAzul", false);
  }

  private void revisarDesbloqueos() {
    boliDesbloqueada.add(0);
    if(boliVerdeDesbloqueada){boliDesbloqueada.add(1);}
    if(boliRojaDesbloqueada){boliDesbloqueada.add(3);}
    if(boliAzulDesbloqueada){boliDesbloqueada.add(2);}
  }

  private void cargarMonedas() {
    //monedas = 1000;
    Preferences prefs = Gdx.app.getPreferences("monedas");
    monedas = prefs.getFloat("MONEDA", 0);
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
    crearBtnSelect(); //Aquí se checan las preferencias para ver si están desbloqueadas las otras skins. Si están desbloqueadas, se crea el btnSelect, si no, se crea el btnDesbloquear.
    crearBtnSelected(); //Se revisan las preferencias para ver la Boli seleccionada y se pone el btnSelected con la Boli del color seleccionado.
  }

  private void crearBtnSelected() {
    cargarSkin();
    ImageButton btnSelected = new GameButton("buttons/btnSelected.png", "buttons/btnSelectedPicado.png");
    switch ((int)texturaElegida){
      case 0: //Boli morada
        btnSelected.setPosition(ANCHO_PANTALLA*0.52f, ALTO_PANTALLA * 0.7f, Align.center);
        break;
      case 1: //Boli Verde
        btnSelected.setPosition(ANCHO_PANTALLA*0.52f, ALTO_PANTALLA * .55f, Align.center);
        break;
      case 2: //Boli Azul
        btnSelected.setPosition(ANCHO_PANTALLA*0.52f, ALTO_PANTALLA*0.25f, Align.center);
        break;
      case 3: //Boli Roja
        btnSelected.setPosition(ANCHO_PANTALLA*0.52f, ALTO_PANTALLA*0.4f, Align.center);
        break;
    }
    skinsStage.addActor(btnSelected);
  }

  private void crearBtnUnlock3() { //Boli Azul
    final ImageButton btnUnlock = new GameButton("buttons/btnUnlock200.png", "buttons/btnUnlock200Picado.png");
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.63f, ALTO_PANTALLA * 0.25f, Align.center);
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(monedas >= 200){
          texturaBoliElegida = "characters/boliAzul.png";
          texturaElegida = 2;
          boliAzulDesbloqueada = true;
          boliDesbloqueada.add(2);
          monedas -= 200;
          btnUnlock.setVisible(false);
          guardarPreferencias();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      }
    });
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnUnlock2() {  //Boli Roja
    final ImageButton bntUnlock = new GameButton("buttons/btnUnlock150.png", "buttons/btnUnlock150Picado.png");
    bntUnlock.setPosition(ANCHO_PANTALLA * 0.63f, ALTO_PANTALLA * 0.4f, Align.center);
    bntUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(monedas >= 150){
          texturaBoliElegida = "characters/boliRoja.png";
          texturaElegida = 3;
          boliRojaDesbloqueada = true;
          boliDesbloqueada.add(3);
          monedas -= 150;
          bntUnlock.setVisible(false);
          guardarPreferencias();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      }
    });
    skinsStage.addActor(bntUnlock);
  }

  private void crearBtnUnlock() { //Boli Verde
    final ImageButton btnUnlock = new GameButton("buttons/btnUnlock.png", "buttons/btnUnlockPicado.png");
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.63f, ALTO_PANTALLA * .55f, Align.center);
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(monedas >= 100){
          texturaBoliElegida = "characters/boliVerde.png";
          texturaElegida = 1;
          boliVerdeDesbloqueada = true;
          boliDesbloqueada.add(1);
          monedas -= 100;
          btnUnlock.setVisible(false);
          guardarPreferencias();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      }
    });
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnSelect() { //Boli Morada por defecto
    //Botón que funciona como bandera, se ve cuando ya se desbloqueó la skin previamente.
    //Tenemos que hacer que primero desbloquee las skins para poder verlo.
    //Si tiene el valor de 1, significa que Boli morada está desbloqueada y se crea un botón específico para seleccionar esa Boli.
    if(boliDesbloqueada.contains(0, true)){
      ImageButton btnSelectMorada = new GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
      btnSelectMorada.setPosition(ANCHO_PANTALLA * 0.5f, ALTO_PANTALLA * 0.7f, Align.center);
      btnSelectMorada.addListener(new ClickListener() {
        public void clicked(InputEvent event, float x, float y){
          super.clicked(event, x, y);
          texturaBoliElegida = "characters/boli_morado.png";
          texturaElegida = 0;
          guardarPreferencias();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      });
      skinsStage.addActor(btnSelectMorada);
    }
    if(boliDesbloqueada.contains(1, true)){
      ImageButton btnSelectVerde = new GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
      btnSelectVerde.setPosition(ANCHO_PANTALLA * 0.5f, ALTO_PANTALLA * 0.55f, Align.center);
      btnSelectVerde.addListener(new ClickListener() {
        public void clicked(InputEvent event, float x, float y){
          super.clicked(event, x, y);
          texturaBoliElegida = "characters/boli_morado.png";
          texturaElegida = 1;
          guardarPreferencias();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      });
      skinsStage.addActor(btnSelectVerde);
    } else {crearBtnUnlock();}
    if(boliDesbloqueada.contains(3, true)){
      ImageButton btnSelectRoja = new GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
      btnSelectRoja.setPosition(ANCHO_PANTALLA * 0.5f, ALTO_PANTALLA * 0.4f, Align.center);
      btnSelectRoja.addListener(new ClickListener() {
        public void clicked(InputEvent event, float x, float y){
          super.clicked(event, x, y);
          texturaBoliElegida = "characters/boli_morado.png";
          texturaElegida = 3;
          guardarPreferencias();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      });
      skinsStage.addActor(btnSelectRoja);
    } else {crearBtnUnlock2();}
    if(boliDesbloqueada.contains(2, true)){
      ImageButton btnSelectAzul = new GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
      btnSelectAzul.setPosition(ANCHO_PANTALLA * 0.5f, ALTO_PANTALLA * 0.25f, Align.center);
      btnSelectAzul.addListener(new ClickListener() {
        public void clicked(InputEvent event, float x, float y){
          super.clicked(event, x, y);
          texturaBoliElegida = "characters/boli_morado.png";
          texturaElegida = 2;
          guardarPreferencias();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      });
      skinsStage.addActor(btnSelectAzul);
    } else {crearBtnUnlock3();}
  }

  private void crearBtnBack() {
    skinsStage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));
  }

  @Override
  public void render(float delta) {
    cleanScreen();
    crearBtnSelected();
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
    dibujarTexto();
    batch.end();

    skinsStage.draw();
  }

  private void hacerSaltarBoli(float tiempo) {
    timerSalto += tiempo;
    if (timerSalto >= 40){
      boliCentral.setEstadoBoli(EstadoBoli.SALTANDO);
      boliCentral.saltar();
      timerSalto = 0;
    }
  }

  private void dibujarTexto() {
    gameText.mostrarMensaje(batch, "Your coins: ", ANCHO_PANTALLA*0.7f, ALTO_PANTALLA * 0.9f);
    gameText.mostrarMensaje(batch, "" + monedas, ANCHO_PANTALLA*0.82f, ALTO_PANTALLA*0.9f);
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

    Preferences unlockGreen = Gdx.app.getPreferences("desbloquearVerde");
    unlockGreen.putBoolean("desbloqueoVerde", boliVerdeDesbloqueada);

    Preferences unlockRed = Gdx.app.getPreferences("desbloquearRoja");
    unlockRed.putBoolean("desbloqueoRoja", boliRojaDesbloqueada);

    Preferences unlockBlue = Gdx.app.getPreferences("desbloquearAzul");
    unlockBlue.putBoolean("desbloqueoAzul", boliAzulDesbloqueada);

    Preferences coins = Gdx.app.getPreferences("monedas");
    coins.putFloat("MONEDA", monedas);

    prefs.flush();  // OBLIGATORIO
    unlockGreen.flush();
    unlockRed.flush();
    unlockBlue.flush();
    coins.flush();
  }

  private void cargarSkin() {
    Preferences prefs = Gdx.app.getPreferences("elegir");
    texturaElegida = prefs.getFloat("SKIN", 0);
  }
}
