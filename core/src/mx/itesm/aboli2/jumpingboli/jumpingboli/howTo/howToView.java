package mx.itesm.aboli2.jumpingboli.jumpingboli.howTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantalla;
import mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton;

public class howToView extends Pantalla {
    Stage howToStage;
    //Fondo
    private Texture texturaFondo;
    private float alturaFondo;

    public howToView(GdXGame game) { super(game);}

    @Override
    public void show() {
        howToStage = new Stage(super.viewport);
        texturaFondo = new Texture("fondos/fondoHow.png");
        alturaFondo = -ALTO_PANTALLA;
        createHowToView();
        Gdx.input.setInputProcessor(howToStage);
    }

    private void createHowToView() {
        createBtnBack();
        createBtnUp();
        createBtnDown();
    }

    private void createBtnDown() {
        ImageButton btnFlechaAbajo = new mx.itesm.aboli2.jumpingboli.jumpingboli.button.GameButton("buttons/btnFlecha.png", "buttons/btnFlechaPicado.png");
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
        howToStage.addActor(btnFlechaAbajo);
    }

    private void createBtnUp() {
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
        howToStage.addActor(btnFlechaArriba);
    }

    private void createBtnBack() {
        ImageButton backBtn = game.buttonFactory.returnToMenuBtn();
        howToStage.addActor(backBtn);
    }

    @Override
    public void render(float delta) {
        cleanScreen();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texturaFondo, 0, alturaFondo);
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
