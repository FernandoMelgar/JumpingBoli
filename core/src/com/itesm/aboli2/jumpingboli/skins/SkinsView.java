package com.itesm.aboli2.jumpingboli.skins;

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
import com.itesm.aboli2.jumpingboli.game.GameView;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class SkinsView extends Pantalla {

  //Escena
  Stage skinsStage;

  //Fondo
  private Texture texturaFondo;

  //Texturas Boli


  //Textura plataforma
  private Texture texturaPlataforma;

  public SkinsView(GdXGame game) {
    super(game);
  }

  @Override
  public void show() {
   skinsStage = new Stage(super.viewport);
   texturaFondo = new Texture("fondos/fondoExtra.png");
   texturaPlataforma = new Texture("characters/plataformaFondo.png");
   createSkinsView();
   Gdx.input.setInputProcessor(skinsStage);
  }

  private void createSkinsView() {
    crearBtnBack();
    crearBtnSelect();
    //Checar preferencias donde guardemos si el jugador ya desbloqueó los niveles para ver si se crean o no.
    crearBtnUnlock();
    crearBtnUnlock2();
    crearBtnUnlock3();
  }

  private void crearBtnUnlock3() {
    //Botón unlock
    Texture texturaBtnUnlock = new Texture("buttons/btnUnlock3.png");
    TextureRegionDrawable trdBtnUnlock = new TextureRegionDrawable(new TextureRegion(texturaBtnUnlock));
    //Botón cómo unlock picado
    Texture btnUnlockPicado = new Texture("buttons/btnUnlockPicado3.png");
    TextureRegionDrawable trdBtnUnlockPicado = new TextureRegionDrawable(new TextureRegion(btnUnlockPicado));
    ImageButton btnUnlock = new ImageButton(trdBtnUnlock, trdBtnUnlockPicado);
    btnUnlock.setPosition(ANCHO_PANTALLA*0.69f, ALTO_PANTALLA*0.2f, Align.center);
    //Acción botón
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        //Acción
      }
    });
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnUnlock2() {
    //Botón unlock
    Texture texturaBtnUnlock = new Texture("buttons/btnUnlock2.png");
    TextureRegionDrawable trdBtnUnlock = new TextureRegionDrawable(new TextureRegion(texturaBtnUnlock));
    //Botón cómo unlock picado
    Texture btnUnlockPicado = new Texture("buttons/btnUnlockPicado2.png");
    TextureRegionDrawable trdBtnUnlockPicado = new TextureRegionDrawable(new TextureRegion(btnUnlockPicado));
    ImageButton btnUnlock = new ImageButton(trdBtnUnlock, trdBtnUnlockPicado);
    btnUnlock.setPosition(ANCHO_PANTALLA*0.69f, ALTO_PANTALLA*0.35f, Align.center);
    //Acción botón
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        //Acción
      }
    });
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnUnlock() {
    //Botón unlock
    Texture texturaBtnUnlock = new Texture("buttons/btnUnlock.png");
    TextureRegionDrawable trdBtnUnlock = new TextureRegionDrawable(new TextureRegion(texturaBtnUnlock));
    //Botón cómo unlock picado
    Texture btnUnlockPicado = new Texture("buttons/btnUnlockPicado.png");
    TextureRegionDrawable trdBtnUnlockPicado = new TextureRegionDrawable(new TextureRegion(btnUnlockPicado));
    ImageButton btnUnlock = new ImageButton(trdBtnUnlock, trdBtnUnlockPicado);
    btnUnlock.setPosition(ANCHO_PANTALLA*0.69f, ALTO_PANTALLA/2, Align.center);
    //Acción botón
    btnUnlock.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        //Acción
      }
    });
    skinsStage.addActor(btnUnlock);
  }

  private void crearBtnSelect() {
    //Botón select
    Texture texturaBtnSelect = new Texture("buttons/btnSelect.png");
    TextureRegionDrawable trdBtnSelect = new TextureRegionDrawable(new TextureRegion(texturaBtnSelect));
    //Botón select picado
    Texture btnSelectPicado = new Texture("buttons/btnSelectPicado.png");
    TextureRegionDrawable trdBtnSelectPicado = new TextureRegionDrawable(new TextureRegion(btnSelectPicado));
    ImageButton btnSelect = new ImageButton(trdBtnSelect, trdBtnSelectPicado);
    btnSelect.setPosition(ANCHO_PANTALLA*0.5f, ALTO_PANTALLA*0.65f, Align.center);
    //Acción botón
    btnSelect.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y){
        super.clicked(event, x, y);
        //Acción
      }
    });
    skinsStage.addActor(btnSelect);
  }

  private void crearBtnBack() {
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
    skinsStage.addActor(btnBack);
  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(texturaFondo, 0, 0);
    batch.draw(texturaPlataforma, ANCHO_PANTALLA*0.15f, ALTO_PANTALLA/6);
    batch.end();

    skinsStage.draw();
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
