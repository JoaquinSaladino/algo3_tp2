package edu.fiuba.algo3.modelo.AccionNocturna;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public abstract class AccionNocturna {
    private Jugador objetivo;

    public abstract void resolver();

    public abstract void insertarEn(List<AccionNocturna> acciones);
}