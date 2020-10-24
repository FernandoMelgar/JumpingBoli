package com.itesm.aboli2.jumpingboli;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.itesm.aboli2.jumpingboli.game.GameView;

public class Boli extends GameObject {

  private float yBase;
  private final float V0 = 850;
  private final float G = 2000;
  private float tVuelo;
  private float tAire; // tiempo de simulacion < tvuelo
  private EstadoBoli estado = EstadoBoli.INICIANDO;
  private EstadoBuff estadoBuff;
  private GameView.EstadoJuego estadoJuego;
  private float DX = 4.5f;
  private float timer;



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
    float delta  = Gdx.graphics.getDeltaTime();
    actualizarTimer(delta);

    if (timer >= 3){
      actualizar();
    }

    if(estado == EstadoBoli.SALTANDO){
      tAire += delta;
      float y = yBase + V0*tAire - 0.5f*G*tAire*tAire;
      sprite.setY(y);
      Gdx.app.log("SALTA", "tAire:" + tAire);
      if (tAire>=tVuelo){
        sprite.setY(yBase);
        estado = EstadoBoli.RODANDO;
      }

    }
    super.render(batch);
  }

  private void actualizarTimer(float delta) {
    timer += delta;
    if (estado == EstadoBoli.INICIANDO && timer>=3) {
      estado = EstadoBoli.RODANDO;
    }
  }

  public void setEstadoBoli(EstadoBoli nuevoEstado){
    estado = nuevoEstado;
  }
  public void setEstadoBuff(EstadoBuff nuevoEstado){
    estadoBuff = nuevoEstado;
  }

}
