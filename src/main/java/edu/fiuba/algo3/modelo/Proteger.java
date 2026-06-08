package edu.fiuba.algo3.modelo;

public class Proteger implements HabilidadNocturna {

    @Override
    public void ejecutar(Jugador objetivo) {
        objetivo.proteger();
    }
}
