package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class LluviaJuego extends Game {
    private Sound sonidoHerido;
    private Sound sonidoGota;
    private Music musicaLluvia;

    @Override
    public void create() {
        // Cargar recursos de audio
        sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        sonidoGota = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        musicaLluvia = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // Configurar la música de fondo
        musicaLluvia.setLooping(true);
        musicaLluvia.play();

        // Inicia el juego con la pantalla de inicio
        setScreen(new PantallaInicio(this));
    }

    // Métodos para acceder a los sonidos desde otras clases
    public Sound getSonidoHerido() {
        return sonidoHerido;
    }

    public Sound getSonidoGota() {
        return sonidoGota;
    }

    public Music getMusicaLluvia() {
        return musicaLluvia;
    }

    @Override
    public void dispose() {
        // Liberar recursos de audio
        sonidoHerido.dispose();
        sonidoGota.dispose();
        musicaLluvia.dispose();
        super.dispose();
    }

    // Método para habilitar el zigzag en las bombas
    public void activarMovimientoZigzagParaBombas() {
        if (getScreen() instanceof PantallaJuego) { // Verifica que la pantalla actual sea de juego
            PantallaJuego pantallaJuego = (PantallaJuego) getScreen();
            pantallaJuego.activarZigzagEnBombas();
        }
    }
}
