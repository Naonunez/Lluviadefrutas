package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class ObjetoCaidaBuilder {
    private Texture textura;
    private MovimientoStrategy estrategiaMovimiento = new MovimientoRecto(); // Por defecto
    private int x = 0, y = 0; // Coordenadas iniciales
    private float velocidad = 5; // Velocidad por defecto

    public ObjetoCaidaBuilder setTextura(Texture textura) {
        if (textura == null) {
            throw new IllegalArgumentException("La textura no puede ser null");
        }
        this.textura = textura;
        return this;
    }

    public ObjetoCaidaBuilder setEstrategiaMovimiento(MovimientoStrategy estrategiaMovimiento) {
        this.estrategiaMovimiento = estrategiaMovimiento;
        return this;
    }

    public ObjetoCaidaBuilder setPosicionInicial(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ObjetoCaidaBuilder setVelocidad(float velocidad) {
        this.velocidad = velocidad;
        return this;
    }

    public ObjetoCaida build() {
        if (textura == null) {
            throw new IllegalStateException("La textura debe estar configurada antes de construir el objeto");
        }

        ObjetoCaida objeto = new ObjetoCaida(estrategiaMovimiento, x, y) {
            @Override
            public void efecto(Tarro tarro) {
                // Implementación específica del efecto
            }
        };
        objeto.setVelocidad(velocidad);
        return objeto;
    }
}
