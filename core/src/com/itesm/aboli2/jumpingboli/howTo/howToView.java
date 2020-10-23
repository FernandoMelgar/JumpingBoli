package com.itesm.aboli2.jumpingboli.howTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class howToView extends Pantalla {
    Stage howToStage;
    //Fondo
    private Texture texturaFondo;

    public howToView(GdXGame game) { super(game);}

    @Override
    public void show() {
        howToStage = new Stage(super.viewport);
        texturaFondo = new Texture("fondos/fondoExtra.png");
        createHowToView();
        Gdx.input.setInputProcessor(howToStage);
    }

    private void createHowToView() {
        createBtnBack();
    }

    private void createBtnBack() {
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
        howToStage.addActor(btnBack);
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
