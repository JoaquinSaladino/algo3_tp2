package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.AEliminar;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;

public class Eliminar implements HabilidadNocturna {

    @Override
    public AccionNocturna ejecutar(Jugador autor,Jugador objetivo) {

        return new AEliminar(autor,objetivo);
    }

    @Override
    public void validarObjetivo(Jugador autor, Jugador posibleObjetivo) {
        if (!posibleObjetivo.estaVivo())
            throw new ObjetivoInvalidoException();
        if (posibleObjetivo.esMafia())
            throw new ObjetivoInvalidoException();
    }
}
