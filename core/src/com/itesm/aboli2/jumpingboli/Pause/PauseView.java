package com.itesm.aboli2.jumpingboli.Pause;

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
import com.itesm.aboli2.jumpingboli.configuration.ConfigurationView;

public class PauseView extends Pantalla {
    
    //Menú-botones.
    private Stage pauseStage;

    //Fondo
    private Texture texturaFondoPausa;
    
    public PauseView(GdXGame game) {
        super(game);
    }

    @Override
    public void show() {
        pauseStage = new Stage(super.viewport);
        texturaFondoPausa = new Texture("fondos/pantallaPausa.png");
        createPause();
        Gdx.input.setInputProcessor(pauseStage);
        
    }


    private void createPause() {
        crearBtnPauJugar();
        crearBtnPauMenu();
        crearBtnPauRetry();
        crearBtnPauConfig();
    }

    private void crearBtnPauConfig() {
        //Botón configuración
        Texture texturaBtnPauConfig = new Texture("buttons/btn_Pausa_Config.png");
        TextureRegionDrawable trdBtnPauConfig = new TextureRegionDrawable(new TextureRegion(texturaBtnPauConfig));
        ImageButton btnPauConfig = new ImageButton(trdBtnPauConfig);
        btnPauConfig.setPosition(ANCHO_PANTALLA*0.95f, ALTO_PANTALLA*0.90f, Align.center);
        //Acción botón
        btnPauConfig.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                game.setScreen(new ConfigurationView(game));
            }
        });
        pauseStage.addActor(btnPauConfig);
    }

    private void crearBtnPauRetry() {
        //Botón reintentar
        Texture texturaBtnPauRetry = new Texture("buttons/btn_Pausa_Retry.png");
        TextureRegionDrawable trdBtnPauRetry = new TextureRegionDrawable(new TextureRegion(texturaBtnPauRetry));
        ImageButton btnPauRetry = new ImageButton(trdBtnPauRetry);
        btnPauRetry.setPosition(ANCHO_PANTALLA*0.95f, ALTO_PANTALLA*0.90f, Align.center);
        //Acción botón
        btnPauRetry.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                game.setScreen(new ConfigurationView(game));
            }
        });
        pauseStage.addActor(btnPauRetry);
    }

    private void crearBtnPauMenu() {
        //Botón regresar al Menu
        Texture texturaBtnPauMenu = new Texture("buttons/btn_Pausa_Menu.png");
        TextureRegionDrawable trdBtnPauMenu = new TextureRegionDrawable(new TextureRegion(texturaBtnPauMenu));
        ImageButton btnPauMenu = new ImageButton(trdBtnPauMenu);
        btnPauMenu.setPosition(ANCHO_PANTALLA*0.95f, ALTO_PANTALLA*0.90f, Align.center);
        //Acción botón
        btnPauMenu.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                game.setScreen(new ConfigurationView(game));
            }
        });
        pauseStage.addActor(btnPauMenu);
    }

    private void crearBtnPauJugar() {
        //Botón reanudar
        Texture texturaBtnPauJugar = new Texture("buttons/btn_Pausa_Jugar.png");
        TextureRegionDrawable trdBtnPauJugar = new TextureRegionDrawable(new TextureRegion(texturaBtnPauJugar));
        ImageButton btnPauJugar = new ImageButton(trdBtnPauJugar);
        btnPauJugar.setPosition(ANCHO_PANTALLA*0.95f, ALTO_PANTALLA*0.90f, Align.center);
        //Acción botón
        btnPauJugar.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                game.setScreen(new ConfigurationView(game));
            }
        });
        pauseStage.addActor(btnPauJugar);
    }

    @Override
    public void render(float delta) {

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
