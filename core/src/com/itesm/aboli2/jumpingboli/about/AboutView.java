package com.itesm.aboli2.jumpingboli.about;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.menu.MenuView;
import com.itesm.aboli2.jumpingboli.skins.SkinsView;

import sun.font.TrueTypeFont;


public class AboutView extends Pantalla {

  //Fondo
  private Texture texturaFondo;
  private float alturaFondo;

  //Texto
  private GameText gameText;
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
    texturaFondo = new Texture("fondos/fondoAbout.png");
    alturaFondo = -ALTO_PANTALLA;
    createText();
    crearButtonLayer();
    Gdx.input.setInputProcessor(escenaHUD);
  }

  private void createText() {
    gameText = new GameText("fuentes/exoFont.fnt");
  }

  private void crearButtonLayer() {
    camaraHUD = new OrthographicCamera(ANCHO_PANTALLA, ALTO_PANTALLA);
    camaraHUD.position.set(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2, 0);
    camaraHUD.update();
    vistaHUD = new StretchViewport(ANCHO_PANTALLA, ALTO_PANTALLA, camaraHUD);

    escenaHUD = new Stage(vistaHUD);

    //Creamos los botones para subir y bajar.
    //Botón flecha arriba
    ImageButton btnFlechaArriba = new GameButton("buttons/btnFlechaArriba.png", "buttons/btnFlechaArribaPicado.png");
    btnFlechaArriba.setPosition(ANCHO_PANTALLA*0.92f, ALTO_PANTALLA*0.9f, Align.center);
    //Acción botón
    btnFlechaArriba.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(alturaFondo +720 > 0){
          alturaFondo -= 30;
        }
      }
    });


    //Botón flecha abajo
    ImageButton btnFlechaAbajo = new GameButton("buttons/btnFlecha.png", "buttons/btnFlechaPicado.png");
    btnFlechaAbajo.setPosition(ANCHO_PANTALLA*0.92f, ALTO_PANTALLA*0.1f, Align.center);
    //Acción botón
    btnFlechaAbajo.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        if(alturaFondo < 0){
          alturaFondo += 30;
        }
      }
    });

    escenaHUD.addActor(btnFlechaArriba); //Botón flecha arriba
    escenaHUD.addActor(btnFlechaAbajo); //Botón flecha abajo.
    escenaHUD.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game))); //Botón back.
  }


  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(texturaFondo, 0, alturaFondo);
    batch.end();

    aboutStage.draw();
    escenaHUD.draw();
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
    game.getManager().unload("fondos/fondoAbout.png");
    game.getManager().unload("buttons/btnFlechaArriba.png");
    game.getManager().unload("buttons/btnFlechaArribaPicado.png");
    game.getManager().unload("buttons/btnFlecha.png");
    game.getManager().unload("buttons/btnFlechaPicado.png");
    batch.dispose();
  }
}
