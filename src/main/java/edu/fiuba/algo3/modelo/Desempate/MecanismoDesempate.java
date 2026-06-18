package edu.fiuba.algo3.modelo.Desempate;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public interface MecanismoDesempate {

    Jugador resolver(List<Jugador> empatados);
}
