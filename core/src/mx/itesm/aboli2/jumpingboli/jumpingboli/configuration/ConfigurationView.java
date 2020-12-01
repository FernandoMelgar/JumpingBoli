package mx.itesm.aboli2.jumpingboli.jumpingboli.configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantalla;

public class ConfigurationView extends Pantalla {

  //Fondo
  private Texture texturaFondo;

  //Efecto sonido
  private Sound efectoBoton;

  //Musica del juego
  private boolean playMusic;

  private Stage configurationStage;

  public ConfigurationView(GdXGame game) {
    super(game);
  }


  @Override
  public void show() {
    configurationStage = new Stage(super.viewport);
    texturaFondo = new Texture("fondos/fondoAjustes.png");
    efectoBoton = game.getManager().get("efectosSonido/efectoBoton.wav");
    createConfigView();
    cargarPreferencias();
    guardarPreferencias();
    Gdx.input.setInputProcessor(configurationStage);
  }

  private void guardarPreferencias() {
    Preferences musica = Gdx.app.getPreferences("musica");
    musica.putBoolean("MUSICA", playMusic);

    musica.flush();
  }

  private void cargarPreferencias() {
    cargarPreferenciasMusica();
  }

  private void cargarPreferenciasMusica() {
    Preferences musica = Gdx.app.getPreferences("musica");
    playMusic = musica.getBoolean("MUSICA", true);
  }

  private void createConfigView() {
    createBackBtn();
    crearBtnControlMusica();
  }

  private void crearBtnControlMusica() {
    //Botón On
    Texture btnMusicaPrendido = new Texture("buttons/btnOn.png");
    TextureRegionDrawable trdBtnMusicaPrendido = new TextureRegionDrawable(new TextureRegion(btnMusicaPrendido));
    //Botón Off
    Texture btnMusicaApagado = new Texture("buttons/btnOff.png");
    TextureRegionDrawable trdBtnMusicaApagado = new TextureRegionDrawable(new TextureRegion(btnMusicaApagado));
    //Botón con efecto
    final Button.ButtonStyle estiloPrendido = new Button.ButtonStyle(trdBtnMusicaPrendido, trdBtnMusicaApagado, null);
    final Button.ButtonStyle estiloApagado = new Button.ButtonStyle(trdBtnMusicaApagado, trdBtnMusicaPrendido, null);

    final Button.ButtonStyle Prendido = new ImageButton.ImageButtonStyle(estiloPrendido);
    final Button.ButtonStyle Apagado = new ImageButton.ImageButtonStyle(estiloApagado);
    //Botón final
    final ImageButton btnMusica = new ImageButton(trdBtnMusicaPrendido, trdBtnMusicaApagado);

    btnMusica.setPosition(ANCHO_PANTALLA * 0.61f, ALTO_PANTALLA * 0.58f, Align.center);
    cargarPreferenciasMusica();
    //Le damos el estilo inicial
    if(playMusic){
      btnMusica.setStyle(Prendido);
    } else {
      btnMusica.setStyle(Apagado);
    }
    //Listener
    btnMusica.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(playMusic){
          btnMusica.setStyle(Apagado);
          playMusic = false;
        } else {
          btnMusica.setStyle(Prendido);
          efectoBoton.play();
          playMusic = true;
        }
        guardarPreferencias();
      }
    });

    configurationStage.addActor(btnMusica);
  }

  private void createBackBtn() {
    //Botón back

    configurationStage.addActor(game.buttonFactory.returnToMenuBtn());
  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(texturaFondo, 0, 0);
    batch.end();

    configurationStage.draw();
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
