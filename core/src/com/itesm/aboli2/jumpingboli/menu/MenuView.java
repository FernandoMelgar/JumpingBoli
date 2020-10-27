package com.itesm.aboli2.jumpingboli.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.itesm.aboli2.jumpingboli.howTo.howToView;
import com.itesm.aboli2.jumpingboli.skins.SkinsView;
import com.itesm.aboli2.jumpingboli.about.AboutView;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.configuration.ConfigurationView;
import com.itesm.aboli2.jumpingboli.game.GameView;


public class MenuView extends Pantalla {

  //Menú- botones.
  private Stage menuStage;

  //Fondo
  private Texture texturaFondo;

  public MenuView(GdXGame mainGame) {
    super(mainGame);
//    game = mainGame;
  }

  @Override
  public void show() {
    menuStage = new Stage(super.viewport);
    texturaFondo = new Texture("fondos/FondoPrincipal.png");
    createMenu();
    Gdx.input.setInputProcessor(menuStage);
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
    ImageButton mainText = new GameButton("titles/title.png", "titles/titleHover.png");
    mainText.setPosition(ANCHO_PANTALLA / 2, ALTO_PANTALLA *0.8f, Align.center);
    menuStage.addActor(mainText);
  }

  private void crearBtnAjustes() {
    //Botón ajustes
    Texture texturaBtnAjustes = new Texture("buttons/btnAjustes.png");
    TextureRegionDrawable trdBtnAjustes = new TextureRegionDrawable(new TextureRegion(texturaBtnAjustes));
    ImageButton btnAjustes = new ImageButton(trdBtnAjustes);
    btnAjustes.setPosition(ANCHO_PANTALLA*0.95f, ALTO_PANTALLA*0.90f, Align.center);
    //Acción botón
    btnAjustes.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        game.setScreen(new ConfigurationView(game));
      }
    });
    menuStage.addActor(btnAjustes);
  }

  private void crearBtnComo() {
    //Botón cómo jugar
    ImageButton btnComo = new GameButton("buttons/btnComo.png","buttons/btnComoPicado.png" );
    btnComo.setPosition(ANCHO_PANTALLA/2, ALTO_PANTALLA/5, Align.center);
    //Acción botón
    btnComo.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        game.setScreen(new howToView(game));
      }
    });
    menuStage.addActor(btnComo);
  }

  private void crearBtnSkins() {
    //Botón skins
    Texture texturaBtnSkins = new Texture("buttons/btnSkins.png");
    TextureRegionDrawable trdBtnSkins = new TextureRegionDrawable(new TextureRegion(texturaBtnSkins));
    //Botón skins picado
    Texture btnSkinsPicado = new Texture("buttons/btnSkinsPicado.png");
    TextureRegionDrawable trdBtnSkinsPicado = new TextureRegionDrawable(new TextureRegion(btnSkinsPicado));
    ImageButton btnSkins = new ImageButton(trdBtnSkins, trdBtnSkinsPicado);
    btnSkins.setPosition(ANCHO_PANTALLA/4, ALTO_PANTALLA/5, Align.center);
    //Acción botón
    btnSkins.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        game.setScreen(new SkinsView(game));
      }
    });
    menuStage.addActor(btnSkins);
  }

  private void crearBtnAcerca() {
    //Botón acerca
    Texture texturaBtnAcerca = new Texture("buttons/btnAcerca.png");
    TextureRegionDrawable trdBtnAcerca = new TextureRegionDrawable(new TextureRegion(texturaBtnAcerca));
    //Botón acerca picado
    Texture btnAcercaPicado = new Texture("buttons/btnAcercaPicado.png");
    TextureRegionDrawable trdBtnAcercaPicado = new TextureRegionDrawable(new TextureRegion(btnAcercaPicado));
    ImageButton btnAcerca = new ImageButton(trdBtnAcerca, trdBtnAcercaPicado);
    btnAcerca.setPosition(ANCHO_PANTALLA*0.75f, ALTO_PANTALLA/5, Align.center);
    //Acción botón
    btnAcerca.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        game.setScreen(new AboutView(game));
      }
    });
    menuStage.addActor(btnAcerca);
  }

  private void crearBtnJugar() {
    //Botón jugar
    Texture texturaBtnJugar = new Texture("buttons/btnPlay.png");
    TextureRegionDrawable trdBtnJugar = new TextureRegionDrawable(new TextureRegion(texturaBtnJugar));
    //Botón
    ImageButton btnJugar = new ImageButton(trdBtnJugar);
    btnJugar.setPosition(ANCHO_PANTALLA/2, ALTO_PANTALLA/2, Align.center);
    //Acción botón jugar
    btnJugar.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        game.setScreen(new GameView(game));
      }
    });
    menuStage.addActor(btnJugar);
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
    batch.dispose();
  }
}

