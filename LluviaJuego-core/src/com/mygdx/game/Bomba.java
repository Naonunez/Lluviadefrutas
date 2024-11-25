package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;


public class Bomba extends ObjetoCaida {
	private MovimientoStrategy estrategiaMovimiento;
    private static List<Bomba> bombas; // Lista global de bombas
    

    public Bomba(Texture textura) {
        super(textura);
        if (bombas == null) {
            bombas = new ArrayList<>();
        }
        bombas.add(this);
    }
    public Bomba(int xInicial, int yInicial) {
        super(new MovimientoZigzag(), xInicial, yInicial); // Movimiento zigzag por defecto
    }

    @Override
    public void efecto(Tarro tarro) {
    	tarro.dañar();
        // Incrementar velocidad de todas las bombas
        for (Bomba bomba : bombas) {
        	bomba.setVelocidad(bomba.getVelocidad() + 30000); // Aumentar la velocidad
        }
    }
    @Override
    public void mover() {
    	if (estrategiaMovimiento != null) {
            estrategiaMovimiento.mover(this); // Solo afecta X
        }

        // Movimiento hacia abajo (caída) en el eje Y
    	this.setY(this.getY() - 3);
    	int num = 5 + (int) (Math.random() * 10);
    	Random random = new Random();

    	int decision = random.nextInt(2);
    	if (decision == 1) {
    		this.setX(this.getX() - num);
    	}
    	else {
    		this.setX(this.getX() + num);
    	}
    	
    	
    	
    }
    
}