package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.AProteger;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Jugador;

public class Proteger implements HabilidadNocturna {

    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        return new AProteger(autor,objetivo);
    }
}
