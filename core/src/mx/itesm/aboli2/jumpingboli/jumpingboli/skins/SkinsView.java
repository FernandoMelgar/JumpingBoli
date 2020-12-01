package mx.itesm.aboli2.jumpingboli.jumpingboli.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import mx.itesm.aboli2.jumpingboli.jumpingboli.GameText;
import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantalla;
import mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton;
import mx.itesm.aboli2.jumpingboli.jumpingboli.game.Boli;
import mx.itesm.aboli2.jumpingboli.jumpingboli.game.EstadoBoli;

public class SkinsView extends Pantalla {

  //Escena
  Stage skinsStage;

  //Fondo
  private Texture texturaFondo;
  private Texture texturaFondoMovible;
  private float xFondo;

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
  private Array<Integer> boliDesbloqueada;

  //Boli seleccionada
  private String texturaBoliElegida;
  private float texturaElegida = 0;

  //Timer para el salto de Boli
  private float timerSalto;

  //Efecto sonido
  private Sound efectoBoton;
  private Boolean playMusic;

  //Texto
  private mx.itesm.aboli2.jumpingboli.jumpingboli.GameText gameText;
  private float monedas;

  public SkinsView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {

    // Bloquea la tecla back en el celular
    Gdx.input.setCatchKey(Input.Keys.BACK, true);

    skinsStage = new Stage(super.viewport);
    boliDesbloqueada = new Array<>(4);
    xFondo = 0;
    efectoBoton = game.getManager().get("efectosSonido/efectoBoton.wav");
    cargarPreferenciasMusica(); //Se cargan para poder ver si se reproducirán efectos de sonido o no.
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

  private void cargarPreferenciasMusica() {
    Preferences musica = Gdx.app.getPreferences("musica");
    playMusic = musica.getBoolean("MUSICA", true);
  }

  private void reproducirEfectoBoton(){
    if(playMusic){
      efectoBoton.play();
    }
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
        boliCentral = new Boli((Texture) game.getManager().get("characters/boliMorado62.png"), ANCHO_PANTALLA*0.185f, ALTO_PANTALLA*0.245f);
        break;
      case 1:
        boliCentral = new Boli((Texture) game.getManager().get("characters/boliVerde62.png"), ANCHO_PANTALLA*0.185f, ALTO_PANTALLA*0.245f);
        break;
      case 2:
        boliCentral = new Boli((Texture) game.getManager().get("characters/boliAzul62.png"), ANCHO_PANTALLA*0.185f, ALTO_PANTALLA*0.245f);
        break;
      case 3:
        boliCentral = new Boli((Texture) game.getManager().get("characters/boliRoja62.png"), ANCHO_PANTALLA*0.185f, ALTO_PANTALLA*0.245f);
        break;
    }
    boliMorada = new Boli(new Texture("characters/boli_morado.png"), ANCHO_PANTALLA*0.45f, ALTO_PANTALLA*0.67f);
    boliVerde = new Boli(new Texture("characters/boliVerde.png"), ANCHO_PANTALLA*0.45f, ALTO_PANTALLA*0.52f);
    boliRoja = new Boli(new Texture("characters/boliRoja.png"), ANCHO_PANTALLA*0.45f, ALTO_PANTALLA*0.37f);
    boliAzul = new Boli(new Texture("characters/boliAzul.png"), ANCHO_PANTALLA*0.45f, ALTO_PANTALLA*0.22f);
  }

  private void crearTexturas() {
    texturaFondo = game.getManager().get("fondos/fondoSkins.png");
    texturaFondoMovible = game.getManager().get("fondos/fondoEstrellasMovibles.png");
  }

  private void createText() {
    gameText = new GameText("fuentes/exoFont.fnt");
  }

  private void createSkinsView() {
    crearBtnBack();
    crearBtnSelect(); //Aquí se checan las preferencias para ver si están desbloqueadas las otras skins. Si están desbloqueadas, se crea el btnSelect, si no, se crea el btnDesbloquear.
  }

  private void crearBtnUnlock3() { //Boli Azul
    final ImageButton btnUnlock = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton("buttons/btnUnlock200.png", "buttons/btnUnlock200Picado.png");
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.73f, ALTO_PANTALLA * 0.25f, Align.center);
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(monedas >= 200){
          texturaBoliElegida = "characters/boliAzul62.png";
          texturaElegida = 2;
          boliAzulDesbloqueada = true;
          boliDesbloqueada.add(2);
          monedas -= 200;
          btnUnlock.setVisible(false);
          guardarPreferencias();
          reproducirEfectoBoton();
          crearBtnSelect();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      }
    });
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnUnlock2() {  //Boli Roja
    final ImageButton bntUnlock = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton("buttons/btnUnlock150.png", "buttons/btnUnlock150Picado.png");
    bntUnlock.setPosition(ANCHO_PANTALLA * 0.73f, ALTO_PANTALLA * 0.4f, Align.center);
    bntUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(monedas >= 150){
          texturaBoliElegida = "characters/boliRoja62.png";
          texturaElegida = 3;
          boliRojaDesbloqueada = true;
          boliDesbloqueada.add(3);
          monedas -= 150;
          bntUnlock.setVisible(false);
          guardarPreferencias();
          reproducirEfectoBoton();
          crearBtnSelect();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      }
    });
    skinsStage.addActor(bntUnlock);
  }

  private void crearBtnUnlock() { //Boli Verde
    final ImageButton btnUnlock = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton("buttons/btnUnlock.png", "buttons/btnUnlockPicado.png");
    btnUnlock.setPosition(ANCHO_PANTALLA * 0.73f, ALTO_PANTALLA * .55f, Align.center);
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(monedas >= 100){
          texturaBoliElegida = "characters/boliVerde62.png";
          texturaElegida = 1;
          boliVerdeDesbloqueada = true;
          boliDesbloqueada.add(1);
          monedas -= 100;
          btnUnlock.setVisible(false);
          guardarPreferencias();
          reproducirEfectoBoton();
          crearBtnSelect();
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
      ImageButton btnSelectMorada = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
      btnSelectMorada.setPosition(ANCHO_PANTALLA * 0.6f, ALTO_PANTALLA * 0.7f, Align.center);
      btnSelectMorada.addListener(new ClickListener() {
        public void clicked(InputEvent event, float x, float y){
          super.clicked(event, x, y);
          texturaBoliElegida = "characters/boliMorado62.png";
          texturaElegida = 0;
          guardarPreferencias();
          reproducirEfectoBoton();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      });
      skinsStage.addActor(btnSelectMorada);
    }
    if(boliDesbloqueada.contains(1, true)){
      ImageButton btnSelectVerde = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
      btnSelectVerde.setPosition(ANCHO_PANTALLA * 0.6f, ALTO_PANTALLA * 0.55f, Align.center);
      btnSelectVerde.addListener(new ClickListener() {
        public void clicked(InputEvent event, float x, float y){
          super.clicked(event, x, y);
          texturaBoliElegida = "characters/boliVerde62.png";
          texturaElegida = 1;
          guardarPreferencias();
          reproducirEfectoBoton();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      });
      skinsStage.addActor(btnSelectVerde);
    } else {crearBtnUnlock();}
    if(boliDesbloqueada.contains(3, true)){
      ImageButton btnSelectRoja = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
      btnSelectRoja.setPosition(ANCHO_PANTALLA * 0.6f, ALTO_PANTALLA * 0.4f, Align.center);
      btnSelectRoja.addListener(new ClickListener() {
        public void clicked(InputEvent event, float x, float y){
          super.clicked(event, x, y);
          texturaBoliElegida = "characters/boliRoja62.png";
          texturaElegida = 3;
          guardarPreferencias();
          reproducirEfectoBoton();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      });
      skinsStage.addActor(btnSelectRoja);
    } else {crearBtnUnlock2();}
    if(boliDesbloqueada.contains(2, true)){
      ImageButton btnSelectAzul = new GameButton("buttons/btnSelect.png", "buttons/btnSelectPicado.png");
      btnSelectAzul.setPosition(ANCHO_PANTALLA * 0.6f, ALTO_PANTALLA * 0.25f, Align.center);
      btnSelectAzul.addListener(new ClickListener() {
        public void clicked(InputEvent event, float x, float y){
          super.clicked(event, x, y);
          texturaBoliElegida = "characters/boliAzul62.png";
          texturaElegida = 2;
          guardarPreferencias();
          reproducirEfectoBoton();
          boliCentral.setTextura(new Texture(texturaBoliElegida));
        }
      });
      skinsStage.addActor(btnSelectAzul);
    } else {crearBtnUnlock3();}
  }

  private void crearBtnBack() {

    ImageButton backBtn = game.buttonFactory.returnToMenuBtn();
    skinsStage.addActor(backBtn);
  }

  @Override
  public void render(float delta) {
    cleanScreen();
    actualizar(delta);
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(texturaFondoMovible, xFondo, 0);
    batch.draw(texturaFondoMovible, xFondo+texturaFondoMovible.getWidth(), 0);
    batch.draw(texturaFondo, 0, 0);
    boliCentral.render(batch);
    boliMorada.render(batch);
    boliVerde.render(batch);
    boliRoja.render(batch);
    boliAzul.render(batch);
    dibujarTexto();
    batch.end();

    skinsStage.draw();
  }

  private void actualizar(float tiempo) {
    hacerSaltarBoli(tiempo);
    actualizarFondo();
  }

  private void actualizarFondo() {
    xFondo-=1;
    if(xFondo == -texturaFondoMovible.getWidth()){
      xFondo=0;
    }
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
    gameText.mostrarMensaje(batch, "Your coins: ", ANCHO_PANTALLA*0.72f, ALTO_PANTALLA * 0.83f);
    gameText.mostrarMensaje(batch, "" + monedas, ANCHO_PANTALLA*0.84f, ALTO_PANTALLA*0.83f);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {
    game.getManager().unload("fondos/fondoSkins.png");
    game.getManager().unload("fondos/fondoEstrellasMovibles.png");
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
