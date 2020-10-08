package com.itesm.aboli2.jumpingboli.configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class ConfigurationView extends Pantalla {

  private Stage stage;

  public ConfigurationView(GdXGame game) {
    super(game);
  }


  @Override
  public void show() {
    stage = new Stage(super.viewport);
    stage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {
    cleanScreen();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(new Texture("fondos/PrototipoAjustes.png"), 0, 0);
    batch.end();

    stage.draw();
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {

  }
}
