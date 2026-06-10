package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.ANula;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Jugador;

public class Nula implements HabilidadNocturna {
    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        return new ANula(autor,objetivo);
    }
}
