package com.itesm.aboli2.jumpingboli.win;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GameText;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.Pantallas;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.loading.PantallaCargando;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class escapeView extends Pantalla {

    private Stage escapeStage;
    private GameText gameText;
    private Sprite spritePlaneta;
    public static final float TIEMPO_ENTRE_FRAMES = 0.05f;
    private float timerAnimacion = TIEMPO_ENTRE_FRAMES;
    private Texture texturaPlaneta;

    // AssetManager
    private AssetManager manager;


    private Texture texturaFondo;
    private Texture texturaNave;
    private Sprite spriteNave;
    private Texture texturaEstela;
    private Sprite spriteEstela;
    private Texture texturaBoli;
    private Sprite spriteBoli;
    private Sprite spriteVictoria;
    private Texture texturaVictoria;
    private float puntos;
    private Texture texturaBtnBack;

    public escapeView(GdXGame game) {
        super(game);
    }

    public escapeView(GdXGame game, float puntos) {
        super(game);
        this.puntos = puntos;
    }


    public void createBtnBack(){
        ImageButton btnReturn = new GameButton("buttons/btnBack.png", "buttons/btnBackPicado.png");
        btnReturn.setPosition(ANCHO_PANTALLA/2, ALTO_PANTALLA - btnReturn.getHeight(), Align.center);
        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MenuView(game));
            }
        });
        escapeStage.addActor(btnReturn);
    }

    @Override
    public void show() {
        escapeStage = new Stage(super.viewport);
        texturaPlaneta = new Texture("iconosFondoEscape/FE_planeta.png");
        spritePlaneta = new Sprite(texturaPlaneta);
        texturaNave = new Texture("iconosFondoEscape/FE_Nave.png");
        spriteNave = new Sprite(texturaNave);
        texturaFondo = new Texture("iconosFondoEscape/FE_Space3.jpg");
        texturaEstela = new Texture("iconosFondoEscape/FE_Estela.png");
        spriteEstela = new Sprite(texturaEstela);
        texturaBoli = new Texture("iconosFondoEscape/FE_Boli.png");
        spriteBoli = new Sprite(texturaBoli);
        texturaVictoria = new Texture("iconosFondoEscape/FE_Letrero.png");
        spriteVictoria = new Sprite(texturaVictoria);
        texturaBtnBack = new Texture("buttons/btnBack.png");
        //cargarRecursos(siguientePantalla);
        gameText = new GameText("fuentes/exoFont.fnt");
        createBtnBack();
        //escapeStage.addActor(ButtonFactory.getPlayBtn(game, new PantallaCargando(game, Pantallas.NIVELUNO)));
        Gdx.input.setInputProcessor(escapeStage);

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (int i = 0; i < 10; i++) {
            batch.draw(texturaFondo,ANCHO_PANTALLA * i,0);

        }
        //batch.draw(texturaFondoCarga,0,0);
        //batch.draw(texturaFondoCarga, ANCHO,0);
        spritePlaneta.draw(batch);
        spriteNave.draw(batch);
        spriteEstela.draw(batch);
        spriteBoli.draw(batch);
        spriteVictoria.draw(batch);
        gameText.mostrarMensaje(batch, "Score:" + " " + (int)puntos + " pts",
                camera.position.x + ANCHO_PANTALLA/4, camera.position.y + ALTO_PANTALLA/3, 800f);
        escapeStage.draw();
        //texto.mostrarMensaje(batch, avance + "%", ANCHO/2, ALTO/1.9f);
        //texto.mostrarMensaje(batch, avance + "%", camara.position.x, camara.position.y + ALTO/2-80);

        /*if (camara.position.x - ANCHO/2 > 0){
            batch.draw(texturaFondoCarga,camara.position.x - ANCHO/2,
                    camara.position.y - ALTO/2);
        }

         */
        batch.end();

        // Actualizar
        timerAnimacion -= delta;
        if (timerAnimacion<=0) {
            timerAnimacion = TIEMPO_ENTRE_FRAMES;
            //spriteCargandoEngranaje.rotate(15);
            spriteBoli.rotate(-95);
        }

        // Actualizar carga
        //actualizarCarga();
        actualizarCamara();
        spritePlaneta.setPosition(camera.position.x + spritePlaneta.getWidth()/4,
                camera.position.y - ALTO_PANTALLA/2);
        spriteNave.setPosition(camera.position.x - ANCHO_PANTALLA/2 + spriteNave.getWidth(),
                camera.position.y + ALTO_PANTALLA/4);
        spriteEstela.setPosition(camera.position.x - ANCHO_PANTALLA/2 + 150,
                camera.position.y - ALTO_PANTALLA/4);
        spriteBoli.setPosition(camera.position.x - ANCHO_PANTALLA/2 + 290,
                camera.position.y - ALTO_PANTALLA/6 + 10);
        spriteVictoria.setPosition(camera.position.x - ANCHO_PANTALLA/2, camera.position.y - ALTO_PANTALLA/2);


    }


    private void actualizarCamara() {
        camera.position.x = camera.position.x + 3;
        camera.update();
    }


    @Override
    public void dispose() {
        texturaPlaneta.dispose();
    }

    //Falta implementar su uso
}
