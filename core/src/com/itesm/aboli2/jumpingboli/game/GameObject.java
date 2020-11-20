package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {

   public Sprite sprite;    // Las subclases pueden acceder/modificar directamente a sprite

  public GameObject(Texture textura, float x, float y) {
    sprite = new Sprite(textura);
    sprite.setPosition(x, y);
  }
  public void setTextura(Texture textura){
    sprite.setTexture(textura);
  }

  public GameObject() {
  }

  public void render(SpriteBatch batch) {
    sprite.draw(batch);
  }

}
