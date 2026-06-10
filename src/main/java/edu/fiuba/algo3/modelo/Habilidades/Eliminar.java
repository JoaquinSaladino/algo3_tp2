package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;

public class Eliminar implements HabilidadNocturna {

    @Override
    public void ejecutar(Jugador objetivo) {
        if (!objetivo.estaVivo())
            throw new ObjetivoInvalidoException();

        if (objetivo.esMafia())
            throw new ObjetivoInvalidoException();

        if (!objetivo.estaProtegido())
            objetivo.eliminar();
    }
}
