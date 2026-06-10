package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.AInvestigar;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Jugador;

public class Investigar implements HabilidadNocturna {
    private boolean resultado;

    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        return new AInvestigar(autor,objetivo);
    }

}
