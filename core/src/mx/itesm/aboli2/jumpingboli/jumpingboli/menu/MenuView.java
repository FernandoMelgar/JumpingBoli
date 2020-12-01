package mx.itesm.aboli2.jumpingboli.jumpingboli.menu;

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

import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantalla;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantallas;
import mx.itesm.aboli2.jumpingboli.jumpingboli.button.ButtonFactory;
import mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton;
import mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView;


public class MenuView extends Pantalla {

  //Menú-botones.
  private Stage menuStage;

  //Efectos de sonido
  private Sound efectoBoton;
  private Sound efectoPlay;
  private boolean playMusic;

  //Fondo
  private Texture texturaFondo;
  private Texture texturaFondoMovible;
  private float xFondo;

  public MenuView(GdXGame mainGame) {
    super(mainGame);
  }

  @Override
  public void show() {

    // Bloquea la tecla back en el celular
    Gdx.input.setCatchKey(Input.Keys.BACK, true);

    menuStage = new Stage(super.viewport);
    texturaFondo = game.getManager().get("fondos/FondoPrincipal.png");
    texturaFondoMovible = game.getManager().get("fondos/fondoEstrellasMovibles.png");
    xFondo = 0;
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
    ImageButton mainText = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton((Texture) game.getManager().get("titles/title.png"),
        (Texture) game.getManager().get("titles/titleHover.png"));

    mainText.setPosition(ANCHO_PANTALLA / 2, ALTO_PANTALLA * 0.8f, Align.center);
    menuStage.addActor(mainText);
  }

  private void crearBtnAjustes() {
    //Botón ajustes
    ImageButton btnAjustes = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton((Texture) game.getManager().get("buttons/btnAjustes.png"));
    btnAjustes.setPosition(ANCHO_PANTALLA*0.95f, ALTO_PANTALLA*0.90f, Align.center);
    //Acción botón
    btnAjustes.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        reproducirEfectoBoton();
        game.setScreen(new mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView(game, Pantallas.CONFIGURACION));
      }
    });
    menuStage.addActor(btnAjustes);
  }

  private void crearBtnComo() {
    //Botón cómo jugar

    ImageButton btnComo = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton((Texture) game.getManager().get("buttons/btnComo.png"),
        (Texture) game.getManager().get("buttons/btnComoPicado.png"));
    btnComo.setPosition(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 5, Align.center);
    //Acción botón
    btnComo.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        reproducirEfectoBoton();
        game.setScreen(new mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView(game, Pantallas.HOWTO));
      }
    });
    menuStage.addActor(btnComo);
  }

  private void crearBtnSkins() {

    ImageButton btnSkins = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton((Texture) game.getManager().get("buttons/btnSkins.png"),
        (Texture) game.getManager().get("buttons/btnSkinsPicado.png"));
    btnSkins.setPosition(ANCHO_PANTALLA / 4, ALTO_PANTALLA / 5, Align.center);
    //Acción botón
    btnSkins.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        reproducirEfectoBoton();
        game.setScreen(new mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView(game, Pantallas.SKINS));
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
        game.setScreen(new mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView(game, Pantallas.ABOUT));
      }
    });
    menuStage.addActor(btnAcerca);
  }

  private void crearBtnJugar() {

    ImageButton btnPlay2 = new ButtonFactory.Builder((Texture) game.getManager().get("buttons/btnPlay.png"))
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
    actualizarFondo();

    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(texturaFondo, 0, 0);
    batch.draw(texturaFondoMovible, xFondo, 0);
    batch.draw(texturaFondoMovible, xFondo+texturaFondoMovible.getWidth(), 0);
    batch.end();
    menuStage.draw();
  }

  private void actualizarFondo() {
    xFondo-=0.5f;
    if(xFondo == -texturaFondoMovible.getWidth()){
      xFondo=0;
    }
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
    game.getManager().unload("fondos/fondoEstrellasMovibles.png");
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

