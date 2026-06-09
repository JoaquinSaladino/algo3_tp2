package edu.fiuba.algo3.modelo;

import java.util.List;

public class Juego {
    private List<Jugador> jugadores;

    public Juego(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void iniciarPartida(){

    }

    public int cantidadJugadores(){
        return jugadores.size();
    }
}
