package com.itesm.aboli2.jumpingboli.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.Pantallas;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.loading.LoadingView;


public class MenuView extends Pantalla {

  //Menú-botones.
  private Stage menuStage;

  //Efectos de sonido
  private Sound efectoInicio;
  private Sound efectoBoton;
  private Sound efectoPlay;
  private boolean playMusic;


  //Fondo
  private Texture texturaFondo;

  public MenuView(GdXGame mainGame) {
    super(mainGame);

//    game = mainGame;
  }

  @Override
  public void show() {
    menuStage = new Stage(super.viewport);
    texturaFondo = game.getManager().get("fondos/FondoPrincipal.png");
    createMenu();
    cargarPreferencias();
    initAudio();
    Gdx.input.setInputProcessor(menuStage);
  }

  private void cargarPreferencias() {
    Preferences musica = Gdx.app.getPreferences("musica");
    playMusic = musica.getBoolean("MUSICA", true);
  }

  private void initAudio() {
    crearEfectos();
  }

  private void crearEfectos() {
    efectoInicio = game.getManager().get("efectosSonido/efectoInicio.wav");
    efectoBoton = game.getManager().get("efectosSonido/efectoBoton.wav");
    efectoPlay = game.getManager().get("efectosSonido/play.ogg");
  }

  private void reproducirEfectoBoton(){
    if(playMusic){
      efectoBoton.play();
    }
  }

  private void createMenu() {
    crearBtnJugar();
    crearBtnAcerca();
    crearBtnSkins();
    crearBtnComo();
    crearBtnAjustes();
    createTitle();
  }

  private void createTitle() {
    ImageButton mainText = new GameButton((Texture) game.getManager().get("titles/title.png"),
        (Texture) game.getManager().get("titles/titleHover.png"));

    mainText.setPosition(ANCHO_PANTALLA / 2, ALTO_PANTALLA * 0.8f, Align.center);
    menuStage.addActor(mainText);
  }

  private void crearBtnAjustes() {
    //Botón ajustes
    ImageButton btnAjustes = new GameButton((Texture) game.getManager().get("buttons/btnAjustes.png"));
    btnAjustes.setPosition(ANCHO_PANTALLA*0.95f, ALTO_PANTALLA*0.90f, Align.center);
    //Acción botón
    btnAjustes.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        reproducirEfectoBoton();
        game.setScreen(new LoadingView(game, Pantallas.CONFIGURACION));
      }
    });
    menuStage.addActor(btnAjustes);
  }

  private void crearBtnComo() {
    //Botón cómo jugar

    ImageButton btnComo = new GameButton((Texture) game.getManager().get("buttons/btnComo.png"),
        (Texture) game.getManager().get("buttons/btnComoPicado.png"));
    btnComo.setPosition(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 5, Align.center);
    //Acción botón
    btnComo.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        reproducirEfectoBoton();
        game.setScreen(new LoadingView(game, Pantallas.HOWTO));
      }
    });
    menuStage.addActor(btnComo);
  }

  private void crearBtnSkins() {

    ImageButton btnSkins = new GameButton((Texture) game.getManager().get("buttons/btnSkins.png"),
        (Texture) game.getManager().get("buttons/btnSkinsPicado.png"));
    btnSkins.setPosition(ANCHO_PANTALLA / 4, ALTO_PANTALLA / 5, Align.center);
    //Acción botón
    btnSkins.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        reproducirEfectoBoton();
        game.setScreen(new LoadingView(game, Pantallas.SKINS));
      }
    });
    menuStage.addActor(btnSkins);
  }

  private void crearBtnAcerca() {
    ImageButton btnAcerca = new GameButton((Texture) game.getManager().get("buttons/btnAcerca.png"),
        (Texture) game.getManager().get("buttons/btnAcercaPicado.png"));
    btnAcerca.setPosition(ANCHO_PANTALLA * 0.75f, ALTO_PANTALLA / 5, Align.center);
    //Acción botón
    btnAcerca.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        reproducirEfectoBoton();
        game.setScreen(new LoadingView(game, Pantallas.ABOUT));
      }
    });
    menuStage.addActor(btnAcerca);
  }

  private void crearBtnJugar() {

    ImageButton btnPlay2 = new ButtonFactory
        .Builder((Texture) game.getManager().get("buttons/btnPlay.png"))
        .position(1280 / 2f, 720 / 2f)
        .alignment(Align.center)
        .clickListener(new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            if (playMusic)
              efectoPlay.play();
            game.setScreen(new LoadingView(game, Pantallas.LEVELSELECTION));
          }
        })
        .build();

    menuStage.addActor(btnPlay2);
  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(texturaFondo, 0, 0);
    batch.end();
    menuStage.draw();
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
    game.getManager().unload("fondos/FondoPrincipal.png");
    game.getManager().unload("buttons/btnAjustes.png");
    game.getManager().unload("buttons/btnComo.png");
    game.getManager().unload("buttons/btnComoPicado.png");
    game.getManager().unload("buttons/btnSkins.png");
    game.getManager().unload("buttons/btnSkinsPicado.png");
    game.getManager().unload("buttons/btnAcerca.png");
    game.getManager().unload("buttons/btnAcercaPicado.png");
    game.getManager().unload("titles/title.png");
    game.getManager().unload("titles/titleHover.png");
    batch.dispose();
  }
}

