package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;

import java.util.List;

public interface Fase {
    void iniciar(List<Jugador> jugadores);
    void ejecutar(List<Jugador> jugadores, RegistroNocturno registro);
    Jugador getJugadorActual(); // Nueva responsabilidad
    boolean avanzarJugador();   // Nueva responsabilidad
    List<String> obtenerObjetivosValidos(List<Jugador> jugadores);
    boolean seleccionarObjetivo(Jugador objetivo);
    String obtenerResumenFase();
}
