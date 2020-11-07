package com.itesm.aboli2.jumpingboli.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameButton extends ImageButton {

  public GameButton(String pathUp, String pathDown) {
    super(new TextureRegionDrawable(new Texture(pathUp)), new TextureRegionDrawable(new Texture(pathDown)));
  }

  public GameButton(String pathUp) {
    super(new TextureRegionDrawable(new Texture(pathUp)));
  }

  public GameButton(Texture textureUp, Texture textureDown) {
    super(new TextureRegionDrawable(textureUp), new TextureRegionDrawable(textureDown));
  }

  public GameButton(Texture textureUp) {
    super(new TextureRegionDrawable(textureUp));
  }
}