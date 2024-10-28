package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<Rectangle> rainDropsPos;
	private Array<Integer> rainDropsType;
	private Array<Texture> frutas;
    private long lastDropTime;
    private Texture bomba;
    private Sound dropSound;
    private Music rainMusic;
	   
	public Lluvia(Array<Texture> frutas,Texture bomba, Sound ss, Music mm) {
		this.rainMusic = mm;
        this.dropSound = ss;
        this.frutas = frutas;  // Asigna el array de texturas de frutas
        this.bomba = bomba;
		
	}
	
	public void crear() {
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();
	}
	
	private void crearGotaDeLluvia() {
		Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);

        // Determinar tipo de gota: 1 para bomba, o un número correspondiente a una fruta.
        int tipoGota = MathUtils.random(1, frutas.size + 1);  // Incluye todas las frutas y la bomba
        rainDropsType.add(tipoGota);
        lastDropTime = TimeUtils.nanoTime();
	   }
	
   public void actualizarMovimiento(Tarro tarro) { 
	   if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();

       for (int i = 0; i < rainDropsPos.size; i++) {
           Rectangle raindrop = rainDropsPos.get(i);
           raindrop.y -= 300 * Gdx.graphics.getDeltaTime();

           // Elimina gotas fuera de la pantalla o que hayan chocado con el tarro
           if (raindrop.y + 64 < 0) {
               rainDropsPos.removeIndex(i);
               rainDropsType.removeIndex(i);
           } else if (raindrop.overlaps(tarro.getArea())) {
               if (rainDropsType.get(i) == 1) {  // Si es una bomba
                   tarro.dañar();
               } else {  // Si es una fruta
                   tarro.sumarPuntos(10);
                   dropSound.play();
               }
               rainDropsPos.removeIndex(i);
               rainDropsType.removeIndex(i);
           }
       }
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) { 
	   for (int i = 0; i < rainDropsPos.size; i++) {
           Rectangle raindrop = rainDropsPos.get(i);
           int tipoGota = rainDropsType.get(i);

           if (tipoGota == 1) {  // Si es una bomba
               batch.draw(bomba, raindrop.x, raindrop.y);
           } else {  // Si es una fruta, selecciona su textura desde el array de frutas
               batch.draw(frutas.get(tipoGota - 2), raindrop.x, raindrop.y);
           }
       }
   }
   public void destruir() {
	      dropSound.dispose();
	      rainMusic.dispose();
   }
   
}
