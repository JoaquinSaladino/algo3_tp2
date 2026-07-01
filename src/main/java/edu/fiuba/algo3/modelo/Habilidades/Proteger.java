package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.AProteger;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;

public class Proteger implements HabilidadNocturna {
    private Jugador objetivoAnterior;
    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        objetivoAnterior = objetivo;
        return new AProteger(autor,objetivo);
    }

    @Override
    public void validarObjetivo(Jugador autor, Jugador posibleObjetivo) {
        if (objetivoAnterior == posibleObjetivo)
            throw new ObjetivoInvalidoException();
    }
}
