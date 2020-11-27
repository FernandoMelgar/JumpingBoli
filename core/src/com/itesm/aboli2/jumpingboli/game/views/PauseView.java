package com.itesm.aboli2.jumpingboli.game.views;

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
import com.itesm.aboli2.jumpingboli.Pantallas;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.loading.LoadingView;

public class PauseView extends Pantalla {
    
    //Menú-botones.
    private Stage pauseStage;

    //Fondo
    private Texture texturaFondoPausa;

    //Botón moviéndose
    //private MovBtnConfig movBtnConfig;
    //private Viewport vista;

    private Screen fromScreen;
    public PauseView(GdXGame game, Screen gameView) {
        super(game);
        fromScreen = gameView;
    }

    public PauseView(GdXGame game) {
        super(game);

    }


    @Override
    public void show() {
        pauseStage = new Stage(super.viewport);
        texturaFondoPausa = new Texture("fondos/fondoPausa.png");
        createPause();
        //pauseStage.addActor(ButtonFactory.getPlayBtn(game, new GameView(game)));
        Gdx.input.setInputProcessor(pauseStage);
    }

    private void createPause() {
        crearBtnPauJugar();
        crearBtnPauMenu();
        crearBtnPauRetry();
        //crearBtnPauConfig();
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
                game.setScreen(new ConfigurationViewPause(game));
            }
        });
        pauseStage.addActor(btnPauConfig);
    }



    private void crearBtnPauRetry() {
        //Botón reintentar
        ImageButton btnRetry = new GameButton("buttons/btn_Pausa_Retry.png", "buttons/btn_Pausa_RetryPicado.png");
        btnRetry.setPosition(ANCHO_PANTALLA*0.80f, ALTO_PANTALLA*0.20f, Align.center);
        /*
        Texture texturaBtnPauRetry = new Texture("buttons/btn_Pausa_Retry.png");
        TextureRegionDrawable trdBtnPauRetry = new TextureRegionDrawable(new TextureRegion(texturaBtnPauRetry));
        ImageButton btnRetry = new ImageButton(trdBtnPauRetry);
        btnRetry.setPosition(ANCHO_PANTALLA*0.80f, ALTO_PANTALLA*0.20f, Align.center);
         */
        //Acción botón
        btnRetry.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                game.setScreen(new LoadingView(game, Pantallas.GAME));

            }
        });
        pauseStage.addActor(btnRetry);
    }


    private void crearBtnPauMenu() {
        ImageButton btnMenu = new GameButton("buttons/btn_Pausa_Menu.png", "buttons/btn_Pausa_MenuPicado.png");
        btnMenu.setPosition(ANCHO_PANTALLA * 0.20f, ALTO_PANTALLA * 0.20f, Align.center);
        //Acción botón
        btnMenu.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new LoadingView(game, Pantallas.MENU));

            }
        });
        pauseStage.addActor(btnMenu);
    }


    private void crearBtnPauJugar() {
        //Botón reanudar
        ImageButton btnJugar = new GameButton("buttons/btn_Pausa_Jugar.png", "buttons/btn_Pausa_JugarPicado.png");
        btnJugar.setPosition(1280/2f, 720/2f, Align.center);
        /*
        Texture texturaBtnPauJugar = new Texture("buttons/btn_Pausa_Jugar.png");
        TextureRegionDrawable trdBtnPauJugar = new TextureRegionDrawable(new TextureRegion(texturaBtnPauJugar));
        ImageButton btnPauJugar = new ImageButton(trdBtnPauJugar);
        btnPauJugar.setPosition(1280/2f, 720/2f, Align.center);
         */
        //Acción botón
        btnJugar.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                Pantalla gameView = (Pantalla) fromScreen;
                game.setScreen(gameView);
            }
        });
        pauseStage.addActor(btnJugar);
    }


    @Override
    public void render(float delta) {
        cleanScreen();
        // rgb(180,19,1)
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texturaFondoPausa, 0, 0);
        batch.end();
        pauseStage.draw();

    }

    @Override
    public void dispose() {

    }
}
