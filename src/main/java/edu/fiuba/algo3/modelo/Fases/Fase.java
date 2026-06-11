package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public interface Fase {
    void ejecutar(List<Jugador> jugadores);
}
