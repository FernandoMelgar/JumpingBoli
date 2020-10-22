package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boli extends GameObject {

  private float yBase;
  private final float V0 = 850;
  private final float G = 2000;
  private float tVuelo;
  private float tAire; // tiempo de simulacion < tvuelo
  private EstadoBoli estado;
  private EstadoBuff estadoBuff;
  private float DX = 4.5f;

  public Boli(Texture textura, float x, float y) {
    super(textura, x, y);
    estado = EstadoBoli.RODANDO;
    estadoBuff = EstadoBuff.NORMAL;
    yBase = y;
  }

  private void actualizar(){
    sprite.setX(sprite.getX() + DX);
  }

  public float getDX(){
    return DX;
  }


  public void saltar(){
    estado = EstadoBoli.SALTANDO;
    tAire = 0;
    tVuelo = 2*V0/G;
  }

  public EstadoBoli getEstado(){
    return estado;
  }

  public void render(SpriteBatch batch){
    actualizar();

    float delta  = Gdx.graphics.getDeltaTime();

    if(estado == EstadoBoli.SALTANDO){
      tAire += delta;
      float y = yBase + V0*tAire - 0.5f*G*tAire*tAire;
      sprite.setY(y);
      if (tAire>=tVuelo){
        sprite.setY(yBase);
        estado = EstadoBoli.RODANDO;
      }

    }
    super.render(batch);
  }

  public void setEstadoBoli(EstadoBoli nuevoEstado){
    estado = nuevoEstado;
  }
  public void setEstadoBuff(EstadoBuff nuevoEstado){
    estadoBuff = nuevoEstado;
  }

}
