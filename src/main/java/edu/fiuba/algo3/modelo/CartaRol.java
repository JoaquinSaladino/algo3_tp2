package edu.fiuba.algo3.modelo;

public abstract class CartaRol {
    private HabilidadNocturna habilidadNocturna;
    public void ejecutarAccionNocturna(Jugador objetivo) {
        habilidadNocturna.ejecutar(objetivo);
    };
    public abstract boolean esMafia();
}