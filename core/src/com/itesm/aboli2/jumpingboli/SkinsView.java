package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class SkinsView extends Pantalla {

  Stage stage;
  public SkinsView(GdXGame game) {
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
    batch.draw(new Texture("fondos/PrototipoSkins.png"), 0, 0);
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
