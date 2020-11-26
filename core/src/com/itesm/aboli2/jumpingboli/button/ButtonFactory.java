package com.itesm.aboli2.jumpingboli.button;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.itesm.aboli2.jumpingboli.GdXGame;
import com.itesm.aboli2.jumpingboli.game.views.LevelSelectionView;

public class ButtonFactory {

  Sound soundEfect;
  Texture textureUp;
  Texture textureDown;
  ClickListener clickListener;

  public static class Builder {
    private final Texture textureUp;
    private Texture textureDown;
    private ClickListener clickListener;
    private float xCoordinate;
    private float yCoordinate;
    private int alignment;

    public Builder(Texture tUp) {
      this.textureUp = tUp;
      yCoordinate = 0;
      xCoordinate = 0;
    }

    public Builder textureDown(Texture tDown) {
      textureDown = tDown;
      return this;
    }

    public Builder clickListener(ClickListener cLinstener) {
      clickListener = cLinstener;
      return this;
    }

    public Builder position(float x, float y) {
      xCoordinate = x;
      yCoordinate = y;
      return this;
    }

    public Builder alignment(int align) {
      alignment = align;
      return this;
    }


    public ImageButton build() {
      ImageButton btn;
      if (textureDown != null)
        btn = new ImageButton(new TextureRegionDrawable(textureUp), new TextureRegionDrawable(textureDown));
      else
        btn = new ImageButton(new TextureRegionDrawable(textureUp));
      btn.setPosition(xCoordinate, yCoordinate, alignment);
      btn.addListener(clickListener);

      return btn;
    }

  }


  public static ImageButton getPlayBtn(final GdXGame context, final Screen toScreen) {
    ImageButton btnToPlay = new GameButton("buttons/btnPlay.png");
    btnToPlay.setPosition(1280 / 2f, 720 / 2f, Align.center);
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
