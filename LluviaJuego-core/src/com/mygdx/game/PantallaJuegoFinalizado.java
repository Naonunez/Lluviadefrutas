package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;

public class PantallaJuegoFinalizado implements Screen {
    private final LluviaJuego juego;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture gameOverImage;

    public PantallaJuegoFinalizado(LluviaJuego juego) {
        this.juego = juego;
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Cargar la imagen de Game Over
        gameOverImage = new Texture("gameover.png");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(gameOverImage, 0, 0, 800, 480);
        batch.end();

        // Reinicia el juego si se detecta un toque de pantalla o clic
        if (Gdx.input.isTouched()) {
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
    }
}
