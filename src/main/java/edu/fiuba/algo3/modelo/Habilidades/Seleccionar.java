package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.ASeleccionar;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;

public class Seleccionar  implements HabilidadNocturna {

    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        return new ASeleccionar(autor,objetivo);
    }

    @Override
    public void validarObjetivo(Jugador autor, Jugador posibleObjetivo) {
        if (!posibleObjetivo.estaVivo()) {
            throw new ObjetivoInvalidoException();
        }
        if (posibleObjetivo.esMafia()) {
            throw new ObjetivoInvalidoException();
        }
    }
}