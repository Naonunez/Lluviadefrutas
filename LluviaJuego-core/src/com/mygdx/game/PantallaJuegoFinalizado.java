
package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class PantallaJuegoFinalizado implements Screen {

    private final LluviaJuego juego;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture gameOverImage;
    private BitmapFont font;

    public PantallaJuegoFinalizado(LluviaJuego juego) {
        this.juego = juego;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.batch = new SpriteBatch();

        // Cargar imagen de Game Over
        this.gameOverImage = new Texture("gameover.png");

        // Inicializar la fuente para el puntaje
        this.font = new BitmapFont();
        font.getData().setScale(2); // Ajustar tamaño de fuente
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Dibuja la imagen de fondo
        batch.draw(gameOverImage, 0, 0, 800, 480);

        // Muestra el puntaje final desde el Singleton
        AdministradorPuntaje puntaje = AdministradorPuntaje.obtenerInstancia();
        font.draw(batch, "Tu puntaje final es: " + puntaje.obtenerPuntaje(), 300, 50);

        batch.end();

        // Reiniciar el juego si se detecta un toque
        if (Gdx.input.isTouched()) {
            puntaje.reiniciarPuntaje(); // Reinicia el puntaje para la próxima partida
            juego.setScreen(new PantallaJuego(juego));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        gameOverImage.dispose();
        font.dispose();
    }
}
