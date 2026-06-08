package edu.fiuba.algo3.modelo;

public class Jugador {
    private String nombre;
    private boolean vivo;
    private CartaRol carta;
    private boolean protegido;

    public void proteger() {
        protegido = true;
    }

    public void eliminar() {
        vivo = false;
    }

    public void usarHabilidad(Jugador objetivo) {
        carta.ejecutarAccionNocturna(objetivo);
    }
    public boolean esMafia() {
        return carta.esMafia();
    }
}
