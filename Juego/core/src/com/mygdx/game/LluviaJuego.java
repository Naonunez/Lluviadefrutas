package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class LluviaJuego extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	   
	private Tarro tarro;
	private Lluvia lluvia;
	@Override
	public void create () {
		font = new BitmapFont();
	    Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	    tarro = new Tarro(new Texture(Gdx.files.internal("canasta.png")), hurtSound);

	    Array<Texture> frutas = new Array<>();
	    frutas.add(new Texture(Gdx.files.internal("uva.png")));
	    frutas.add(new Texture(Gdx.files.internal("platano.png")));
	    frutas.add(new Texture(Gdx.files.internal("frutilla.png")));
	    frutas.add(new Texture(Gdx.files.internal("cherry.png")));
	    frutas.add(new Texture(Gdx.files.internal("mango.png")));
	    frutas.add(new Texture(Gdx.files.internal("manzana.png")));  
	    frutas.add(new Texture(Gdx.files.internal("piña.png")));
	    frutas.add(new Texture(Gdx.files.internal("kiwi.png")));  
	    frutas.add(new Texture(Gdx.files.internal("sandia.png")));  

	    Texture bomba = new Texture(Gdx.files.internal("bomba.png"));
	    Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	    Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

	    lluvia = new Lluvia(frutas, bomba, dropSound, rainMusic);

	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    batch = new SpriteBatch();

	    tarro.crear();
	    lluvia.crear();
	}
	


	@Override
	public void render () {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0.53f, 0.81f, 0.92f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 720, 475);
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();        
			// caida de la lluvia 
	        lluvia.actualizarMovimiento(tarro);	   
		}
		
		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
		
		batch.end();	
		
	}
	
	@Override
	public void dispose () {
	      tarro.destruir();
        lluvia.destruir();
	      batch.dispose();
	      font.dispose();
	}
}
