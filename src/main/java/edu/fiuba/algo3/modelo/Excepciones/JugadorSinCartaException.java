package edu.fiuba.algo3.modelo.Excepciones;

public class JugadorSinCartaException extends RuntimeException {

    public JugadorSinCartaException() {
        super("El jugador no tiene una carta asignada");
    }
}