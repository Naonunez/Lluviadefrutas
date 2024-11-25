package com.mygdx.game;

public class MovimientoZigzag implements MovimientoStrategy {
    private boolean haciaDerecha = true;

    @Override
    public void mover(ObjetoCaida objeto) {
    	if (haciaDerecha) {
            objeto.setX(objeto.getX() + 2); // Mueve hacia la derecha
        } else {
            objeto.setX(objeto.getX() - 2); // Mueve hacia la izquierda
        }
        objeto.setY(objeto.getY() - 5); // Mueve hacia abajo
        haciaDerecha = !haciaDerecha; // Cambia de dirección horizontal
        System.out.println("Movimiento zigzag aplicado: x=" + objeto.getX() + ", y=" + objeto.getY());
    
    }
}