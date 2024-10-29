package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia implements Actualizable {
    private Array<ObjetoCaida> objetosCaidos;
    private Array<Texture> frutas;
    private Texture bomba;
    private Sound dropSound;
    private Music rainMusic;
    private long lastDropTime;

    public Lluvia(Array<Texture> frutas, Texture bomba, Sound ss, Music mm) {
        this.frutas = frutas;
        this.bomba = bomba;
        this.dropSound = ss;
        this.rainMusic = mm;
    }

    public void crear() {
        objetosCaidos = new Array<>();
        crearObjetoCaido();
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearObjetoCaido() {
        ObjetoCaida nuevoObjeto;
        if (MathUtils.randomBoolean(0.2f)) {  // 20% probabilidad de bomba
            nuevoObjeto = new Bomba(bomba);
        } else {
            int tipoFruta = MathUtils.random(0, frutas.size - 1);
            nuevoObjeto = new Fruta(frutas.get(tipoFruta));
        }
        nuevoObjeto.getArea().setPosition(MathUtils.random(0, 800 - 64), 480);
        objetosCaidos.add(nuevoObjeto);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void actualizarMovimiento(Tarro tarro) { 
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) crearObjetoCaido();

        for (int i = 0; i < objetosCaidos.size; i++) {
            ObjetoCaida objeto = objetosCaidos.get(i);
            objeto.getArea().y -= 200 * Gdx.graphics.getDeltaTime();

            if (objeto.getArea().y + 64 < 0) {
                objetosCaidos.removeIndex(i);
            } else if (objeto.getArea().overlaps(tarro.getArea())) {
                objeto.efecto(tarro);
                if (objeto instanceof Fruta) dropSound.play();
                objetosCaidos.removeIndex(i);
            }
        }
    }


    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (ObjetoCaida objeto : objetosCaidos) {
            batch.draw(objeto.getTextura(), objeto.getArea().x, objeto.getArea().y);
        }
    }

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }
}
