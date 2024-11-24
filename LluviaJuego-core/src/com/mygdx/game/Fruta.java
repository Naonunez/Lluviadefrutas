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

    @Override
    public void actualizarMovimiento(Tarro tarro) {
        super.actualizarMovimiento(tarro); // Usa el movimiento base y puedes agregar lógica extra
    }
}
