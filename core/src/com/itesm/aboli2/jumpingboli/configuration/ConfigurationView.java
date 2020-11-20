package com.itesm.aboli2.jumpingboli.configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.menu.MenuView;
import com.itesm.aboli2.jumpingboli.skins.Seleccion;

public class ConfigurationView extends Pantalla {

  //Fondo
  private Texture texturaFondo;

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
          playMusic = true;
        }
        guardarPreferencias();
      }
    });

    configurationStage.addActor(btnMusica);
  }

  private void createBackBtn() {
    //Botón back
    Texture texturaBtnBack = new Texture("buttons/btnBack.png");
    TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
    //Botón cómo back picado
    Texture btnBackPicado = new Texture("buttons/btnBackPicado.png");
    TextureRegionDrawable trdBtnBackPicado = new TextureRegionDrawable(new TextureRegion(btnBackPicado));
    ImageButton btnBack = new ImageButton(trdBtnBack, trdBtnBackPicado);
    btnBack.setPosition(ANCHO_PANTALLA*0.10f, ALTO_PANTALLA*0.88f, Align.center);
    //Acción botón
    btnBack.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        game.setScreen(new MenuView(game));
      }
    });
    configurationStage.addActor(btnBack);
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
