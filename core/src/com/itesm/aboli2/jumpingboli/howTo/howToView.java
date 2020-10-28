package com.itesm.aboli2.jumpingboli.howTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class howToView extends Pantalla {
    Stage howToStage;
    //Fondo
    private Texture texturaFondo;

    public howToView(GdXGame game) { super(game);}

    @Override
    public void show() {
        howToStage = new Stage(super.viewport);
        texturaFondo = new Texture("fondos/fondoHow.png");
        createHowToView();
        Gdx.input.setInputProcessor(howToStage);
    }

    private void createHowToView() {
        createBtnBack();
    }

    private void createBtnBack() {
        howToStage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));
    }

    @Override
    public void render(float delta) {
        cleanScreen();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texturaFondo, 0, 0);
        batch.end();

        howToStage.draw();
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
