package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaJuego implements Screen {
    private final LluviaJuego juego;
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
	   
    private Tarro tarro;
    private Lluvia lluvia;

    public PantallaJuego(LluviaJuego juego) {
        this.juego = juego;
        font = new BitmapFont();
        batch = new SpriteBatch();

        // Inicialización de objetos del juego
        tarro = new Tarro(new Texture("canasta.png"), juego.getSonidoHerido());

        Array<Texture> frutas = new Array<>();
        frutas.add(new Texture("uva.png"));
        frutas.add(new Texture("platano.png"));
        frutas.add(new Texture("frutilla.png"));
        frutas.add(new Texture("cherry.png"));
        frutas.add(new Texture("mango.png"));
        frutas.add(new Texture("manzana.png"));  
        frutas.add(new Texture("piña.png"));
        frutas.add(new Texture("kiwi.png"));  
        frutas.add(new Texture("sandia.png"));  

        Texture bomba = new Texture("bomba.png");
        lluvia = new Lluvia(frutas, bomba, juego.getSonidoGota(), juego.getMusicaLluvia());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {
        tarro.crear();
        lluvia.crear();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.53f, 0.81f, 0.92f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Puntaje frutal: " + tarro.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 720, 475);

        if (!tarro.estaHerido()) {
            tarro.actualizarMovimiento(tarro);
            lluvia.actualizarMovimiento(tarro);
        }

        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
        batch.end();

        // Al cambiar a la pantalla final, guarda el puntaje en el Singleton
        if (tarro.getVidas() <= 0) {
            AdministradorPuntaje.obtenerInstancia().reiniciarPuntaje(); // Limpia puntajes previos
            AdministradorPuntaje.obtenerInstancia().agregarPuntos(tarro.getPuntos()); // Guarda el puntaje actual
            juego.setScreen(new PantallaJuegoFinalizado(juego));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        tarro.destruir();
        lluvia.destruir();
        batch.dispose();
        font.dispose();
    }
}