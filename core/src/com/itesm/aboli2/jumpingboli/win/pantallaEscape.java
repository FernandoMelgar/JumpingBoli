package com.itesm.aboli2.jumpingboli.win;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;

public class pantallaEscape extends Pantalla {

    private Sprite spritePlaneta;
    public static final float TIEMPO_ENTRE_FRAMES = 0.05f;
    private float timerAnimacion = TIEMPO_ENTRE_FRAMES;
    private Texture texturaPlaneta;

    // AssetManager
    private AssetManager manager;

    // Referencia al juego
    private Juego juego;    // Para hacer setScreen

    // Identifica las pantallas del juego
    private Pantallas siguientePantalla;

    private Texto texto;
    private Texture texturaFondo;
    private Texture texturaNave;
    private Sprite spriteNave;
    private Texture texturaEstela;
    private Sprite spriteEstela;
    private Texture texturaBoli;
    private Sprite spriteBoli;
    private Sprite spriteVictoria;
    private Texture texturaVictoria;

    public pantallaEscape(GdXGame game) {
        super(game);
    }

    @Override
    public void show() {
        texturaPlaneta = new Texture("pruebasBoli/FE_planeta.png");
        spritePlaneta = new Sprite(texturaPlaneta);
        spritePlaneta.setPosition(ANCHO - spritePlaneta.getWidth()/2,
                0);
        texturaNave = new Texture("pruebasBoli/FE_Nave.png");
        spriteNave = new Sprite(texturaNave);
        spriteNave.setPosition(ANCHO - ANCHO/8,
                ALTO - ALTO/8);
        texturaFondo = new Texture("pruebasBoli/FE_space.png");
        texturaEstela = new Texture("pruebasBoli/FE_Estela.png");
        spriteEstela = new Sprite(texturaEstela);
        spriteEstela.setPosition(ANCHO - ANCHO/8,
                ALTO - ALTO/7);
        texturaBoli = new Texture("pruebasBoli/FE_Boli.png");
        spriteBoli = new Sprite(texturaBoli);
        spriteBoli.setPosition(ANCHO - ANCHO/8,
                ALTO - ALTO/7);
        texturaVictoria = new Texture("pruebasBoli/FE_Letrero.png");
        spriteVictoria = new Sprite(texturaVictoria);
        cargarRecursos(siguientePantalla);
        texto = new Texto("runner/game.fnt");
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0.5f, 0.2f, 0.5f);

        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        for (int i = 0; i < 10; i++) {
            batch.draw(texturaFondo,ANCHO * i,0);

        }
        //batch.draw(texturaFondoCarga,0,0);
        //batch.draw(texturaFondoCarga, ANCHO,0);
        spritePlaneta.draw(batch);
        spriteNave.draw(batch);
        spriteEstela.draw(batch);
        spriteBoli.draw(batch);
        spriteVictoria.draw(batch);
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
        spritePlaneta.setPosition(camara.position.x + spritePlaneta.getWidth()/4,
                camara.position.y - ALTO/2);
        spriteNave.setPosition(camara.position.x - ANCHO/2 + spriteNave.getWidth(),
                camara.position.y + ALTO/4);
        spriteEstela.setPosition(camara.position.x - ANCHO/2 + 150,
                camara.position.y - ALTO/4);
        spriteBoli.setPosition(camara.position.x - ANCHO/2 + 290,
                camara.position.y - ALTO/6 + 10);
        spriteVictoria.setPosition(camara.position.x - ANCHO/2, camara.position.y - ALTO/2);

    }

    private void actualizarCamara() {
        camara.position.x = camara.position.x + 3;
        camara.update();
    }


    @Override
    public void dispose() {
        texturaPlaneta.dispose();
    }

    //Falta implementar su uso
}
