package com.mygdx.game;

public class MovimientoRecto implements MovimientoStrategy {
    @Override
    public void mover(ObjetoCaida objeto) {
        objeto.setY(objeto.getY() - 5); // Baja el objeto recto hacia abajo
    }
}