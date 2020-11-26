package com.itesm.aboli2.jumpingboli.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
import com.itesm.aboli2.jumpingboli.button.GameButton;
import com.itesm.aboli2.jumpingboli.loading.LoadingView;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

public class LevelSelectionView extends Pantalla {

  private Texture backgroundLevelSelection;
  private Stage levelSelectionStage;
  private float nivelEscogido;

  public LevelSelectionView(GdXGame context) {
    super(context);
  }


  @Override
  public void show() {
    cargarNivel();

    levelSelectionStage = new Stage(super.viewport);
    backgroundLevelSelection = new Texture("fondos/fondoPausa.png");

    ImageButton firstLevel = new GameButton("buttons/levelselection/btnLevel1_active.png",
        "buttons/levelselection/btnLevel1_clicked.png");
    firstLevel.setPosition(ANCHO_PANTALLA * .3f, ALTO_PANTALLA * .5f, Align.center);
    firstLevel.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        game.setScreen(new LoadingView(game, Pantallas.NIVELUNO));
        nivelEscogido = 0;
        guardarPreferencias();
      }
    });

    levelSelectionStage.addActor(firstLevel);
    levelSelectionStage.addActor(ButtonFactory.getReturnBtn(game, new MenuView(game)));

    loadBtn2();
    Gdx.input.setInputProcessor(levelSelectionStage);
  }

  private void loadBtn2() {
    ImageButton secondLevel;
    if (isLevelOneCompleted()){
      secondLevel = new GameButton("buttons/levelselection/btnLevel2_active.png",
              "buttons/levelselection/btnLevel2_clicked.png");
      secondLevel.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          game.setScreen(new LoadingView(game, Pantallas.NIVELUNO));
          nivelEscogido = 1;
          guardarPreferencias();
        }
      });


    } else{
      secondLevel = new GameButton("buttons/levelselection/btnLevel2_blocked.png",
              "buttons/levelselection/btnLevel2_blocked_clicked.png");



    }
    secondLevel.setPosition(ANCHO_PANTALLA * .7f, ALTO_PANTALLA * .5f, Align.center);
    levelSelectionStage.addActor(secondLevel);

  }

  public boolean isLevelOneCompleted() {
    return true;
    //Preferences completo = Gdx.app.getPreferences("estaCompleto");
    //return completo.getBoolean("ESTACOMPLETO", false);
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
