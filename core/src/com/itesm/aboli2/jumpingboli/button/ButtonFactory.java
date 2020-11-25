package com.itesm.aboli2.jumpingboli.button;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.game.views.LevelSelectionView;

public class ButtonFactory {

  public static ImageButton getPlayBtn(final GdXGame context, final Screen toScreen){
    ImageButton btnToPlay = new GameButton("buttons/btnPlay.png");
    btnToPlay.setPosition(1280/2f, 720/2f, Align.center);
    btnToPlay.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        context.setScreen(toScreen);
      }
    });
    return btnToPlay;
  }


  public static ImageButton getReturnBtn(final GdXGame context, final Screen toScreen) {
    ImageButton btnReturn = new GameButton("buttons/btnBack.png", "buttons/btnBackPicado.png");
    btnReturn.setPosition(1280 * .1f, 720 * .9f, Align.center);
    btnReturn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        context.setScreen(toScreen);
      }
    });
    return btnReturn;
  }

  public static ImageButton toLevelSelectView(final GdXGame context) {
    ImageButton btnReturn = new GameButton("buttons/btnBack.png", "buttons/btnBackPicado.png");
    btnReturn.setPosition(1280 / 2f, 720 / 2f, Align.center);
    btnReturn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        context.setScreen(new LevelSelectionView(context));
      }
    });
    return btnReturn;
  }
}
