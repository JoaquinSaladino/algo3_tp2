package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Jugador;

public interface HabilidadNocturna {
    AccionNocturna ejecutar(Jugador autor, Jugador objetivo);
}
