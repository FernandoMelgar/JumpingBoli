package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.org.apache.xerces.internal.impl.ExternalSubsetResolver;

public class Boli extends GameObject {

  private float yBase;
  private float V0 = 850;
  private final float G = 2000;
  private float tVuelo;
  private float tAire; // tiempo de simulacion < tvuelo
  private EstadoBoli estado;
  private EstadoBuff estadoBuff;
  private float DX = 4.5f;
  public float DY = -8f;
  private float V;

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

  public void cayendo(){
    tAire = 0;
    yBase = sprite.getY();
    estado = EstadoBoli.CAYENDO;

  }

  public double getV(){
    return V;
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
      if(tAire >= tVuelo/2){
        yBase = sprite.getY();
        tAire = 0;
        estado = EstadoBoli.CAYENDO;
      }
      /*
      if (tAire>=tVuelo){
        sprite.setY(yBase);
        estado = EstadoBoli.RODANDO;
      }

       */
    }
    if(estado == EstadoBoli.CAYENDO){
      tAire += delta;
      V = yBase -0.5f*G*tAire*tAire;
       sprite.setY(V);
    }
    super.render(batch);
  }

  public void setEstadoBoli(EstadoBoli nuevoEstado){
    estado = nuevoEstado;
  }
  public void setEstadoBuff(EstadoBuff nuevoEstado){
    estadoBuff = nuevoEstado;
  }

  public float getX() {
    return sprite.getX();
  }

  public float getY() {
    return sprite.getY();
  }

  public void setVi(float i) {
    V0 = i;
  }

  public void setyBase(float i) {
    yBase = i;
  }

  public void setPosicion(float x, int i) {
    sprite.setPosition(x,i);
  }
}
