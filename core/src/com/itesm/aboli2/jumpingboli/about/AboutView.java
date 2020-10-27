package com.itesm.aboli2.jumpingboli.about;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.Texto;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.menu.MenuView;


public class AboutView extends Pantalla {

  //Fondo
  private Texture texturaFondo;

  //Texto
  private Texto texto;
  private float puntos;

  //HUD
  private Stage escenaHUD;
  private OrthographicCamera camaraHUD;
  private Viewport vistaHUD;


  private Stage aboutStage;
  BitmapFont font = new BitmapFont();

  public AboutView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
    aboutStage = new Stage(super.viewport);
    texturaFondo = new Texture("fondos/fondoExtra.png");
    createAboutView();
    createText();
    crearHUD();
    Gdx.input.setInputProcessor(escenaHUD);
  }

  private void createText() {texto = new Texto("fuentes/exoFont.fnt");}

  private void crearHUD() {
    camaraHUD = new OrthographicCamera(ANCHO_PANTALLA, ALTO_PANTALLA);
    camaraHUD.position.set(ANCHO_PANTALLA/2, ALTO_PANTALLA/2, 0);
    camaraHUD.update();
    vistaHUD = new StretchViewport(ANCHO_PANTALLA, ALTO_PANTALLA, camaraHUD);

    escenaHUD = new Stage(vistaHUD);

    //Creamos el botón back
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
    escenaHUD.addActor(btnBack);

  }

  private void createAboutView() {

  }


  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(texturaFondo, 0, 0);
    dibujarTexto();
    batch.end();

    aboutStage.draw();
    escenaHUD.draw();

  }

  private void dibujarTexto() {
    texto.mostrarMensaje(batch, "About us:", ANCHO_PANTALLA/2, ALTO_PANTALLA*0.9f);
    texto.mostrarMensaje(batch, "Creators", ANCHO_PANTALLA/2, ALTO_PANTALLA*0.85f);
    texto.mostrarMensaje(batch, "Fernando Manuel Melgar Fuentes - A01748354\n" +
            "Alex Fernando Leyva Martinez - A01747078\n" +
            "Arturo Márquez Olivar - A01376086\n" +
            "Claudio Mayoral Garcia - A01747749", ANCHO_PANTALLA/2, ALTO_PANTALLA*0.8f);



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
