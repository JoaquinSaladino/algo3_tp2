package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.Jugador;

public class Investigar implements HabilidadNocturna {
    private boolean resultado;

    @Override
    public void ejecutar(Jugador objetivo) {
        this.resultado = objetivo.esMafia();
    }
    public boolean getResultado() {
        return resultado;
    }
}
