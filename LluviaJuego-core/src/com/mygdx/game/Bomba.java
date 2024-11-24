package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Bomba extends ObjetoCaida {

    public Bomba(Texture textura) {
        super(textura);
    }

    @Override
    public void efecto(Tarro tarro) {
        tarro.dañar();
    }

    @Override
    public void actualizarMovimiento(Tarro tarro) {
        super.actualizarMovimiento(tarro); 
    }
}
