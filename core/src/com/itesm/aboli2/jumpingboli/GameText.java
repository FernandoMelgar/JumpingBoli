package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;


public class GameText {
  private BitmapFont font;

  public GameText(String archivo) {
    font = new BitmapFont(Gdx.files.internal(archivo));
  }

  public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y) {
    GlyphLayout glyp = new GlyphLayout();
    glyp.setText(font, mensaje);
    float anchoTexto = glyp.width;
    font.draw(batch, glyp, x - anchoTexto / 2, y);
  }

  public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y, float fontSize) {
    GlyphLayout glyp = new GlyphLayout();
    glyp.setText(font, mensaje);
    float anchoTexto = fontSize;
    glyp.setText(font, mensaje, Color.BLACK, glyp.width, Align.center, false);
    font.draw(batch, glyp, x - anchoTexto / 2, y);
  }
}