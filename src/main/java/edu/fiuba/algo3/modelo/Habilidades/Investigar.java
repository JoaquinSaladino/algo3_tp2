package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.AInvestigar;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;

public class Investigar implements HabilidadNocturna {
    private Jugador objetivoAnterior;

    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        if (objetivo == objetivoAnterior)
            throw new ObjetivoInvalidoException();
        objetivoAnterior = objetivo;
        return new AInvestigar(autor,objetivo);
    }

}
