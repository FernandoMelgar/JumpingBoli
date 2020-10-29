package com.itesm.aboli2.jumpingboli.configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.Pause.PauseView;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class ConfigurationViewPause extends Pantalla {

  //Fondo
  private Texture texturaFondo;

  private Stage configurationStage;

  public ConfigurationViewPause(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
    configurationStage = new Stage(super.viewport);
    texturaFondo = new Texture("fondos/fondoAjustes.png");
    createConfigView();
    cargarPreferencias();
    Gdx.input.setInputProcessor(configurationStage);
  }

  private void cargarPreferencias() {
    cargarPreferenciasMusica();
  }

  private void cargarPreferenciasMusica() {
    Preferences prefs = Gdx.app.getPreferences("Musica");
  }

  private void createConfigView() {
    createBackBtn();
    crearBtnControlMusica();
  }

  private void crearBtnControlMusica() {
    //Botón encendido.
    ImageButton btnOn = new GameButton("buttons/btnOn.png", "buttons/btnOff.png");
    btnOn.setPosition(ANCHO_PANTALLA * 0.61f, ALTO_PANTALLA * 0.58f, Align.center);
    btnOn.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
      }
    });

    configurationStage.addActor(btnOn);
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
