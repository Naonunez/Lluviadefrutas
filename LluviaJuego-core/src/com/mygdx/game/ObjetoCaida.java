package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoCaida implements Actualizable {
	private Rectangle area;
	private Texture textura;

    public ObjetoCaida(Texture textura) {
        this.textura = textura;
        area = new Rectangle();
        area.width = 64;
        area.height = 64;
    }

    
    
    public Rectangle getArea() {
        return area;
    }

    public Texture getTextura() {
        return textura;
    }

    @Override
    public void actualizarMovimiento(Tarro tarro) {
        // Movimiento genérico para objetos que caen
        area.y -= 5; // Reduce la posición en Y para simular caída
        if (area.overlaps(tarro.getArea())) {
            efecto(tarro);
        }
    }

    public abstract void efecto(Tarro tarro);
}
