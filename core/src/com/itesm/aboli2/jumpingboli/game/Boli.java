package com.itesm.aboli2.jumpingboli.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.itesm.aboli2.jumpingboli.menu.MenuView;

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
  private float timerPausa;


  public Boli(Texture textura, float x, float y) {
    super(textura, x, y);
    estado = EstadoBoli.RODANDO;
    estadoBuff = EstadoBuff.NORMAL;
    yBase = y;
  }

  public void actualizar(){
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
    float delta  = Gdx.graphics.getDeltaTime();
    actualizarTimer(delta);

    if (timerPausa>=3) {
      sprite.rotate(-10);
    }
    //actualizar();

    /*
    if (estado == EstadoBoli.INICIANDO){
      iniciando();
    }else{
      actualizar();
    }
     */

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

  private void actualizarTimer(float delta) {
    timerPausa += delta;
    if (estado == EstadoBoli.INICIANDO && timerPausa>=3) {
      estado = EstadoBoli.RODANDO;
    }
    // ESPERA 3 SEGUNDOS PARA INICIAR EL MOVIMIENTO DE BOLI
    if (timerPausa > 3){
      actualizar();
    }
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
