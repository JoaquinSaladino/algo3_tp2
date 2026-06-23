package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroObjetivos {

    public List<Jugador> filtrar(Jugador actual, List<Jugador> jugadores){
        return jugadores.stream()
                .filter(j -> j != actual)
                .filter(Jugador::estaVivo)
                .collect(Collectors.toList());
    }
}
