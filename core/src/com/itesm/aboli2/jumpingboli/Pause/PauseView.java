package com.itesm.aboli2.jumpingboli.Pause;

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
import com.itesm.aboli2.jumpingboli.configuration.ConfigurationViewPause;
import com.itesm.aboli2.jumpingboli.loading.PantallaCargando;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

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
        Texture texturaBtnPauRetry = new Texture("buttons/btn_Pausa_Retry.png");
        TextureRegionDrawable trdBtnPauRetry = new TextureRegionDrawable(new TextureRegion(texturaBtnPauRetry));
        ImageButton btnPauRetry = new ImageButton(trdBtnPauRetry);
        btnPauRetry.setPosition(ANCHO_PANTALLA*0.80f, ALTO_PANTALLA*0.20f, Align.center);
        //Acción botón
        btnPauRetry.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);

                game.setScreen(new PantallaCargando(game, Pantallas.NIVELUNO));

            }
        });
        pauseStage.addActor(btnPauRetry);
    }


    private void crearBtnPauMenu() {
        //Botón regresar al Menu
        Texture texturaBtnPauMenu = new Texture("buttons/btn_Pausa_Menu.png");
        TextureRegionDrawable trdBtnPauMenu = new TextureRegionDrawable(new TextureRegion(texturaBtnPauMenu));
        ImageButton btnPauMenu = new ImageButton(trdBtnPauMenu);
        btnPauMenu.setPosition(ANCHO_PANTALLA*0.20f, ALTO_PANTALLA*0.20f, Align.center);
        //Acción botón
        btnPauMenu.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                game.setScreen(new MenuView(game));
            }
        });
        pauseStage.addActor(btnPauMenu);
    }


    private void crearBtnPauJugar() {
        //Botón reanudar
        Texture texturaBtnPauJugar = new Texture("buttons/btn_Pausa_Jugar.png");
        TextureRegionDrawable trdBtnPauJugar = new TextureRegionDrawable(new TextureRegion(texturaBtnPauJugar));
        ImageButton btnPauJugar = new ImageButton(trdBtnPauJugar);
        btnPauJugar.setPosition(1280/2f, 720/2f, Align.center);
        //Acción botón
        btnPauJugar.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                Pantalla gameView = (Pantalla) fromScreen;
                game.setScreen(gameView);
            }
        });
        pauseStage.addActor(btnPauJugar);
    }


    @Override
    public void render(float delta) {
        cleanScreen();
        // rgb(180,19,1)
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texturaFondoPausa, 0, 0);
        //movBtnConfig.draw();
        //movBtnConfig.sprite.draw(batch);
        //movBtnConfig.sprite.rotate(-10);
        batch.end();
        pauseStage.draw();

    }

    @Override
    public void dispose() {

    }
}
