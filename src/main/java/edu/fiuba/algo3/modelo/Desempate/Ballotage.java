package edu.fiuba.algo3.modelo.Desempate;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public class Ballotage implements MecanismoDesempate {

    @Override
    public Jugador resolver(List<Jugador> empatados) {

        return empatados.get(0);
    }
}
