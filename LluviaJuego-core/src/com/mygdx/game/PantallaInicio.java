package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaInicio implements Screen {
    private final LluviaJuego juego;  
    private OrthographicCamera camera;
    private Texture fondo;
    private Texture botonJugar;
    private SpriteBatch batch;

    public PantallaInicio(LluviaJuego juego) {
        this.juego = juego;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        
        fondo = new Texture(Gdx.files.internal("fondo.png"));
        botonJugar = new Texture(Gdx.files.internal("boton.png"));
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); 
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(fondo, 0, 0, 800, 480);
        batch.draw(botonJugar, 300, 100); 
        batch.end();

        
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY(); 
            if (x >= 200 && x <= 500 && y >= 100 && y <= 147) { 
                juego.setScreen(new PantallaJuego(juego)); 
                dispose(); 
                }
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
        fondo.dispose();
        botonJugar.dispose();
        batch.dispose();
    }
}