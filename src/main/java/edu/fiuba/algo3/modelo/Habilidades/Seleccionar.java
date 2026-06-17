package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.AEliminar;
import edu.fiuba.algo3.modelo.AccionNocturna.ASeleccionar;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;

public class Seleccionar  implements HabilidadNocturna {

    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        if (!objetivo.estaVivo())
            throw new ObjetivoInvalidoException();

        if (objetivo.esMafia())
            throw new ObjetivoInvalidoException();

        return new ASeleccionar(autor,objetivo);
    }
}