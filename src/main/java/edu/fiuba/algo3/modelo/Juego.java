package edu.fiuba.algo3.modelo;

import java.util.List;

public class Juego {
    private List<Jugador> jugadores;
    private Turno turnoActual;
    public Juego(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void iniciarPartida(){
        turnoActual = new Turno();
    }

    public int cantidadJugadores(){
        return jugadores.size();
    }
    public void ejecutarFaseActual(){
        turnoActual.ejecutarFaseActual(jugadores);
    }

    public void avanzarFase(){

    }
}
