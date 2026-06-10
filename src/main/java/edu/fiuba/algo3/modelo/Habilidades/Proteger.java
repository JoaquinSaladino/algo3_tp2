package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.AProteger;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;

public class Proteger implements HabilidadNocturna {
    private Jugador objetivoAnterior;
    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        if (objetivoAnterior == objetivo)
            throw new ObjetivoInvalidoException();
        objetivoAnterior = objetivo;
        return new AProteger(autor,objetivo);
    }
}
