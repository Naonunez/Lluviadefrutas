package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;

public class Bomba extends ObjetoCaida {
    private static List<Bomba> bombas; // Lista global de bombas

    public Bomba(Texture textura) {
        super(textura);
        if (bombas == null) {
            bombas = new ArrayList<>();
        }
        bombas.add(this);
    }

    @Override
    public void efecto(Tarro tarro) {
    	tarro.dañar();
        // Incrementar velocidad de todas las bombas
        for (Bomba bomba : bombas) {
        	bomba.setVelocidad(bomba.getVelocidad() + 30000); // Aumentar la velocidad
        }
    }
}
