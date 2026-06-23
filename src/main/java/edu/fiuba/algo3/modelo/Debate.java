package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Debate {

    private List<Jugador> nominados;

    public Debate() {
        this.nominados = new ArrayList<>();
    }

    public void iniciar(List<Jugador> jugadores) {
        nominados.clear();
        for(Jugador jugador : jugadores) {

        }
    }

    public void nominar( Jugador nominado) {

        if (!nominado.estaVivo())
            throw new ObjetivoInvalidoException();

        if (!nominados.contains(nominado))
            nominados.add(nominado);
    }

    public List<String> obtenerObjetivosValidos (Jugador jugadorActual , List<Jugador> jugadores){
        List<String> jugadoresValidos = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            if(jugador!=jugadorActual && jugador.estaVivo()) {
                jugadoresValidos.add(jugador.getNombre());
            }
        }
        return jugadoresValidos;
    }
    public List<Jugador> getNominados() {
        return nominados;
    }

    public boolean estaActivo() {
        return true;
    }
}
