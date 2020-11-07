package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Pantalla implements Screen {


    public static final float ANCHO_PANTALLA = 1280;
    public static final float ALTO_PANTALLA = 720;

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected SpriteBatch batch;
    protected GdXGame game;

    public Pantalla(GdXGame game) {
        camera = new OrthographicCamera();
        camera.position.set(ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2, 0);
        camera.update();
        viewport = new StretchViewport(ANCHO_PANTALLA, ALTO_PANTALLA, camera);
        batch = new SpriteBatch();
        this.game = game;

    }

    protected void cleanScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void paintScreen(float r, float g, float b) {
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void hide() {
//        dispose();
    }

}
