    package com.mateolambis.dominio;

public class Jugador {
    public String nombre;
    public int dinero;

    public Jugador(String nombre, int dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void restarDinero(int cantidad) {
        dinero -= cantidad;
    }

    public void sumarDinero(int cantidad) {
        dinero += cantidad;
    }
}