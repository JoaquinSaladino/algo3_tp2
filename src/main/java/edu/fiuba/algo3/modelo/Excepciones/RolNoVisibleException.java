package edu.fiuba.algo3.modelo.Excepciones;

public class RolNoVisibleException extends RuntimeException {

    public RolNoVisibleException()
    {
        super("No se puede ver el rol de otro jugador");
    }
}