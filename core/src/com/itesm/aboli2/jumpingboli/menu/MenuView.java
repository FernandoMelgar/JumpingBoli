package com.itesm.aboli2.jumpingboli.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.SkinsView;
import com.itesm.aboli2.jumpingboli.about.AboutView;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.configuration.ConfigurationView;
import com.itesm.aboli2.jumpingboli.game.GameView;


public class MenuView extends Pantalla {


  private Stage menuStage;


  public MenuView(GdXGame mainGame) {
    super(mainGame);
//    game = mainGame;
  }

  @Override
  public void show() {
    menuStage = new Stage(super.viewport);
    createTitle();
    createMenu();
    Gdx.input.setInputProcessor(menuStage);
  }

  private void createTitle() {
    ImageButton mainText = new GameButton("titles/title.png", "titles/titleHover.png");
    mainText.setPosition(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2 + ALTO_PANTALLA * .4f, Align.center);
    menuStage.addActor(mainText);

  }

  private void createMenu() {
    menuStage.addActor(ButtonFactory.getPlayBtn(game,  new GameView(game)));
    menuStage.addActor(ButtonFactory.getHowToBtn(game, new GameView(game)));
    menuStage.addActor(ButtonFactory.getAboutBtn(game, new AboutView(game)));
    menuStage.addActor(ButtonFactory.getSkinsBtn(game, new SkinsView(game)));
    menuStage.addActor(ButtonFactory.getConfigurationBtn(game, new ConfigurationView(game)));
  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(new Texture("fondos/FondoPrincipal.png"), 0, 0);
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
    batch.dispose();
  }
}

