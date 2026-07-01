package edu.fiuba.algo3.modelo.Excepciones;

public class ObjetivoInvalidoException extends RuntimeException {

    public ObjetivoInvalidoException()
    {
        super("El objetivo seleccionado no es válido");
    }
}