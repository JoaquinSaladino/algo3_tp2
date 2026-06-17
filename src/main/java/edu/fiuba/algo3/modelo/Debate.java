package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Debate {

    private List<Jugador> nominados;

    public Debate() {
        this.nominados = new ArrayList<>();
    }

    public void iniciar() {
        nominados.clear();
    }

    public void nominar(Jugador nominador, Jugador nominado) {

        if (!nominado.estaVivo())
            throw new ObjetivoInvalidoException();

        if (!nominados.contains(nominado))
            nominados.add(nominado);
    }

    public List<Jugador> getNominados() {
        return nominados;
    }

    public boolean estaActivo() {
        return true;
    }
}
