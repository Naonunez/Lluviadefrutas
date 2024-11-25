package com.mygdx.game;

public class AdministradorPuntaje {

    // Atributo estático para almacenar la instancia única
    private static AdministradorPuntaje instancia;

    // Atributo para almacenar el puntaje
    private int puntaje;

    // Constructor privado para evitar instanciación externa
    private AdministradorPuntaje() {
        this.puntaje = 0; 
    }

    public static AdministradorPuntaje obtenerInstancia() {
        if (instancia == null) {
            instancia = new AdministradorPuntaje();
        }
        return instancia;
    }

   
    public void agregarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    public void restarPuntos(int puntos) {
        this.puntaje -= puntos;
        if (this.puntaje < 0) {
            this.puntaje = 0; 
        }
    }

    public int obtenerPuntaje() {
        return puntaje;
    }

    public void reiniciarPuntaje() {
        this.puntaje = 0;
    }
}
