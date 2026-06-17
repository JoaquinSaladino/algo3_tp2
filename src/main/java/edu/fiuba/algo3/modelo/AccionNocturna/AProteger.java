package edu.fiuba.algo3.modelo.AccionNocturna;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;

import java.util.List;

public class AProteger extends AccionNocturna {

    public AProteger(Jugador autor, Jugador objetivo){
        super(autor,objetivo);
    }

    @Override
    public void resolver(RegistroNocturno registroActual) {
        this.objetivo.proteger();
    }

    @Override
    public void insertarEn(List<AccionNocturna> acciones) {
        acciones.add(this);
    }
}
