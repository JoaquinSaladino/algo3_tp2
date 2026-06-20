package edu.fiuba.algo3.modelo.AccionNocturna;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;

import java.util.List;

public abstract class AccionNocturna {
    protected Jugador autor;
    protected Jugador objetivo;

    public AccionNocturna(Jugador autor,Jugador objetivo){
        this.autor = autor;
        this.objetivo = objetivo;
    }
    public abstract void resolver(RegistroNocturno registroActual);

}