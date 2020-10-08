package com.itesm.aboli2.jumpingboli.button;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import com.itesm.aboli2.jumpingboli.GdXGame;

public class ButtonFactory {

  public static ImageButton getPlayBtn(final GdXGame context, final Screen toScreen){
    ImageButton btnToPlay = new GameButton("buttons/btnPlay.png", "buttons/btnPlayHover.png");
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


  public static ImageButton getReturnBtn(final GdXGame context, final Screen toScreen){
    ImageButton btnReturn = new GameButton("buttons/btnReturn.png");
    btnReturn.setPosition(1280 * .1f, 720 *.9f, Align.center);
    btnReturn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        context.setScreen(toScreen);
      }
    });
    return btnReturn;
  }

  public static ImageButton getConfigurationBtn(final GdXGame context, final Screen toScreen){
    ImageButton btnConfiguration = new GameButton("buttons/ajustes.png");
    btnConfiguration.setPosition(1280 - btnConfiguration.getWidth() * 1.5f, 720 - btnConfiguration.getHeight()*1.5f);
    btnConfiguration.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        context.setScreen(toScreen);
      }
    });
    return btnConfiguration;
  }

  public static ImageButton getHowToBtn(final GdXGame context, final Screen toScreen){
    ImageButton btnHowto = new GameButton("buttons/btnHow.png");
    btnHowto.setPosition(1280*.25f, 720*.3f, Align.center);
    btnHowto.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        context.setScreen(toScreen);
      }
    });
    return btnHowto;
  }

  public static ImageButton getAboutBtn(final GdXGame context, final Screen toScreen){
    ImageButton btnAbout = new GameButton("buttons/btnAbout.png");
    btnAbout.setPosition(1280*.5f, 720*.3f, Align.center);
    btnAbout.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        context.setScreen(toScreen);
      }
    });
    return btnAbout;
  }

  public static ImageButton getSkinsBtn(final GdXGame context, final Screen toScreen){
    ImageButton btnSkins = new GameButton("buttons/btnSkins.png");
    btnSkins.setPosition(1280*.75f, 720*.3f, Align.center);
    btnSkins.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        context.setScreen(toScreen);
      }
    });
    return btnSkins;
  }

  public static ImageButton getOnGameConfigBtn(){
    ImageButton btnOnGameConfig = new GameButton("buttons/ajustes.png");
    btnOnGameConfig.setPosition(1280 - btnOnGameConfig.getWidth()*1.5f, 720 * btnOnGameConfig.getHeight()*1.5f);
    return btnOnGameConfig;
  }
}
