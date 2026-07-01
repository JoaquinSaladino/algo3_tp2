package edu.fiuba.algo3.modelo.CondicionVictoria;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public class CondicionVictoriaMafia implements CondicionVictoria{

    @Override
    public boolean verificarCondicion(List<Jugador> jugadores) {
        int jugadoresMafia = Math.toIntExact(jugadores.stream().filter(jugador -> jugador.esMafia() && jugador.estaVivo()).count());
        int jugadoresCiudadanos = Math.toIntExact(jugadores.stream().filter(jugador -> !jugador.esMafia() && jugador.estaVivo()).count());

        return jugadoresMafia >= jugadoresCiudadanos;
    }
}
