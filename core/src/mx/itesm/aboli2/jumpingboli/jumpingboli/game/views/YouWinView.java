package mx.itesm.aboli2.jumpingboli.jumpingboli.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.aboli2.jumpingboli.jumpingboli.GameText;
import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantalla;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantallas;
import mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton;
import mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView;

public class YouWinView extends Pantalla {


  private Stage youWinStage;
  private mx.itesm.aboli2.jumpingboli.jumpingboli.GameText gameText;
  private float puntos;
  private Texture texturaFondoYouWin;
  private Texture texturaBoli;
  private Sprite spriteBoli;

  ImageButton btnReturn = new GameButton("buttons/btnBack.png", "buttons/btnBackPicado.png");

  public YouWinView(mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame game) {
    super(game);
  }

  public YouWinView(GdXGame game, float puntos) {
    super(game);
    this.puntos = puntos;
  }

  public void createBtnBack(ImageButton btn) {
    btn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        game.setScreen(new LoadingView(game, Pantallas.MENU));
      }
    });
    youWinStage.addActor(btn);
  }

  @Override
  public void show() {

    // Bloquea la tecla back en el celular
    Gdx.input.setCatchKey(Input.Keys.BACK, true);

    cargarPuntos();
    youWinStage = new Stage(super.viewport);
    texturaFondoYouWin = new Texture("fondos/fondoYouWin.png");
    gameText = new GameText("fuentes/exoFont.fnt");
    texturaBoli = new Texture("iconosFondoEscape/FE_Boli2.png");
    spriteBoli = new Sprite(texturaBoli);
    spriteBoli.setPosition(camera.position.x + spriteBoli.getWidth() / 2 + 38,
            camera.position.y - spriteBoli.getHeight() / 2 - 75);
    createBtnBack(btnReturn);
    Gdx.input.setInputProcessor(youWinStage);
    guardarPreferencias();

  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(texturaFondoYouWin, 0, 0);
    gameText.mostrarMensaje(batch, "Score:" + " " + (int) puntos + " pts", ANCHO_PANTALLA * .480f, ALTO_PANTALLA * .840f, 400f);
    btnReturn.setPosition(camera.position.x + 400, camera.position.y - ANCHO_PANTALLA / 4);
    spriteBoli.rotate(-5);
    youWinStage.draw();
    spriteBoli.draw(batch);
    batch.end();

  }

  private void guardarPreferencias() {
    Preferences prefs = Gdx.app.getPreferences("isLevelOneCompleted");
    prefs.putBoolean("ISLevelOneCompleted", true);

    prefs.flush();  // OBLIGATORIO
  }

  private void cargarPuntos() {
    Preferences puntosPre = Gdx.app.getPreferences("puntos");
    puntos = puntosPre.getFloat("PUNTOS", 0);
  }

  @Override
  public void dispose() {
    batch.dispose();
  }

}
