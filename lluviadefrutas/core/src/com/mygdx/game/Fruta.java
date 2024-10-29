package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Fruta extends ObjetoCaida {

    public Fruta(Texture textura) {
        super(textura);
    }

    @Override
    public void efecto(Tarro tarro) {
        tarro.sumarPuntos(10);
    }
}
