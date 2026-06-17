package edu.fiuba.algo3.modelo.AccionNocturna;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;

import java.util.List;

public class ASeleccionar extends AccionNocturna {
    public ASeleccionar(Jugador autor, Jugador objetivo) {
        super(autor, objetivo);
    }

    @Override
    public void resolver(RegistroNocturno registroActual) {
        registroActual.registrarVotoMafia(this.objetivo,this.autor.obtenerCarta());
    }

    @Override
    public void insertarEn(List<AccionNocturna> acciones) {
        // No se inserta directamente en la lista de ejecución final
    }
}