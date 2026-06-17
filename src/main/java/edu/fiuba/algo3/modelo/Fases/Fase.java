package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;

import java.util.List;

public interface Fase {
    void ejecutar(List<Jugador> jugadores, RegistroNocturno registro);
}
