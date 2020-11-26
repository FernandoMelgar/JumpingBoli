package com.itesm.aboli2.jumpingboli.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.Pantalla;
import com.itesm.aboli2.jumpingboli.Pantallas;
import com.itesm.aboli2.jumpingboli.button.ButtonFactory;
import com.itesm.aboli2.jumpingboli.loading.LoadingView;

public class LevelSelectionView extends Pantalla {

    private Texture backgroundLevelSelection;
    private Stage levelSelectionStage;
    private float nivelEscogido;
    private Sound clickedSound;
    private boolean playMusic;

    public LevelSelectionView(GdXGame context) {
        super(context);
    }


    @Override
    public void show() {
        cargarNivel();
        loadAudioEfects();

        levelSelectionStage = new Stage(super.viewport);

        createToLevelOneBtn();
        createLevelTwoBtn();

        ImageButton backBtn = game.buttonFactory.returnToMenuBtn();
        levelSelectionStage.addActor(backBtn);

        Gdx.input.setInputProcessor(levelSelectionStage);
    }

    private void loadAudioEfects() {
        Preferences musica = Gdx.app.getPreferences("musica");
        playMusic = musica.getBoolean("MUSICA", true);
        clickedSound = game.getManager().get("efectosSonido/playLevel.ogg");
    }


    private void createToLevelOneBtn() {
        backgroundLevelSelection = game.getManager().get("fondos/fondoPausa.png");
        ImageButton toFirstLevel = new ButtonFactory
                .Builder((Texture) game.getManager().get("buttons/levelselection/btnLevel1_active.png"))
                .textureDown((Texture) game.getManager().get("buttons/levelselection/btnLevel1_clicked.png"))
                .position(ANCHO_PANTALLA * .3f, ALTO_PANTALLA * .5f)
                .alignment(Align.center)
                .clickListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        nivelEscogido = 0;
                        guardarPreferencias();
                        if (playMusic)
                            clickedSound.play();
                        game.setScreen(new LoadingView(game, Pantallas.GAME));
                    }
                })
                .build();
        levelSelectionStage.addActor(toFirstLevel);
    }

    private void createLevelTwoBtn() {
        Texture textureUp;
        Texture textureDown;

        if (isLevelOneCompleted()) {
            textureUp = game.getManager().get("buttons/levelselection/btnLevel2_active.png");
            textureDown = game.getManager().get("buttons/levelselection/btnLevel2_clicked.png");
        } else {
            textureUp = game.getManager().get("buttons/levelselection/btnLevel2_blocked.png");
            textureDown = game.getManager().get("buttons/levelselection/btnLevel2_blocked_clicked.png");
        }

        ImageButton toSecondLevel = new ButtonFactory
                .Builder(textureUp)
                .textureDown(textureDown)
                .position(ANCHO_PANTALLA * .7f, ALTO_PANTALLA * .5f)
                .alignment(Align.center)
                .clickListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        nivelEscogido = 1;
                        guardarPreferencias();
                        if (isLevelOneCompleted())
                            game.setScreen(new LoadingView(game, Pantallas.GAME));
                    }
                })
                .build();
        levelSelectionStage.addActor(toSecondLevel);

    }

    public boolean isLevelOneCompleted() {
        Preferences pref = Gdx.app.getPreferences("isLevelOneCompleted");
        return pref.getBoolean("isLevelOneCompleted", false);
    }

    @Override
    public void render(float delta) {
        cleanScreen();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundLevelSelection, 0, 0);
        batch.end();
        levelSelectionStage.draw();
    }

    private void cargarNivel() {
        Preferences prefs = Gdx.app.getPreferences("nivel");
        nivelEscogido = prefs.getFloat("NIVEL", 0);

    }
    private void guardarPreferencias() {
        Preferences prefs = Gdx.app.getPreferences("nivel");
        prefs.putFloat("NIVEL", nivelEscogido);

        prefs.flush();  // OBLIGATORIO
    }

    @Override
    public void dispose() {

    }
}