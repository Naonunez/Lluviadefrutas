package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoCaida {
    protected Rectangle area;
    protected Texture textura;

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

    public abstract void efecto(Tarro tarro);
}
