package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoCaida implements Actualizable {
    private Rectangle area;
    private Texture textura;
    protected float velocidad; // Velocidad de caída
    private MovimientoStrategy estrategiaMovimiento;
    private int x, y;

    public ObjetoCaida(Texture textura) {
        this.textura = textura;
        this.area = new Rectangle();
        this.area.width = 64;
        this.area.height = 64;
        this.velocidad = 5; // Velocidad estándar
        
    }
    
    public ObjetoCaida(MovimientoStrategy estrategiaMovimiento, int xInicial, int yInicial) {
        this.estrategiaMovimiento = estrategiaMovimiento;
        this.x = xInicial;
        this.y = yInicial;
    }

    public void actualizarMovimiento(Tarro tarro) {
        mover();
        if (area.overlaps(tarro.getArea())) {
            efecto(tarro);
        }
    }

    protected void moverv() {
    	area.y -= velocidad; // Usa la velocidad para mover el objeto
        
    }

    public abstract void efecto(Tarro tarro);

    public Rectangle getArea() {
        return area;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setVelocidad(float nuevaVelocidad) {
        this.velocidad = nuevaVelocidad;
    }

    public float getVelocidad() {
        return this.velocidad;
    }
    
    public void setEstrategiaMovimiento(MovimientoStrategy estrategiaMovimiento) {
        this.estrategiaMovimiento = estrategiaMovimiento;
    }

    public void mover() {
        if (estrategiaMovimiento != null) {
            estrategiaMovimiento.mover(this);
        }
    }

    public void setX(float x) {
        area.x = x;
    }

    public void setY(float y) {
        area.y = y;
    }

    public float getX() {
        return area.x;
    }

    public float getY() {
        return area.y;
    }

    
}