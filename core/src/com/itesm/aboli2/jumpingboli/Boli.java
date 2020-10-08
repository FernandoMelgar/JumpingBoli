package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.graphics.Texture;

public class Boli extends GameObject {

  private final float DX_Boli = 10;

  public Boli(Texture textura, float x, float y) {
    super(textura, x, y);
  }

  public void moverIzquierda() {
    sprite.setX(sprite.getX() - DX_Boli);
  }

  public void moverDerecha() {
    sprite.setX(sprite.getX() + DX_Boli);
  }
}
