package edu.fiuba.algo3.modelo.Excepciones;

public class MazoVacioException extends RuntimeException {
    public MazoVacioException() {
        super("No quedan cartas en el mazo");
    }
}
