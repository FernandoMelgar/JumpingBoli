package mx.itesm.aboli2.jumpingboli.jumpingboli.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import mx.itesm.aboli2.jumpingboli.jumpingboli.GdXGame;
import mx.itesm.aboli2.jumpingboli.jumpingboli.Pantallas;
import mx.itesm.aboli2.jumpingboli.jumpingboli.loading.LoadingView;

public class ButtonFactory {

  Sound soundEfect;
  GdXGame game;

  public ButtonFactory(GdXGame game) {
    this.game = game;
  }

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

  boolean isSoundActive() {
    Preferences musica = Gdx.app.getPreferences("musica");
    boolean isActive = musica.getBoolean("MUSICA", true);
    if (isActive)
      soundEfect = game.getManager().get("efectosSonido/efectoBoton.wav");
    return isActive;
  }

  public ImageButton returnToMenuBtn() {
    return new Builder((Texture) game.getManager().get("buttons/btnBack.png"))
        .textureDown((Texture) game.getManager().get("buttons/btnBackPicado.png"))
        .position(1280 * .1f, 720 * .9f)
        .alignment(Align.center)
        .clickListener(new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
            Gdx.app.log("ButtonFatory", "Clicked :)))");
            super.clicked(event, x, y);
            if (isSoundActive()) {
              soundEfect.play();
            }
            game.setScreen(new LoadingView(game, Pantallas.MENU));
          }
        })
        .build();
  }

}
