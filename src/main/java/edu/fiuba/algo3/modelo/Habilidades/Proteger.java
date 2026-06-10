package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.Jugador;

public class Proteger implements HabilidadNocturna {

    @Override
    public void ejecutar(Jugador objetivo) {
        objetivo.proteger();
    }
}
