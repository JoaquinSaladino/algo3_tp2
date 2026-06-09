package edu.fiuba.algo3.modelo;

import java.util.List;

public class Turno {
    private Nocturna nocturna;
    private Diurna diurna;
    private Fase faseActual;

    public Turno() {
        this(new Nocturna(), new Diurna());
    }

    public Turno(Nocturna nocturna, Diurna diurna) {
        this.nocturna = nocturna;
        this.diurna = diurna;
        this.faseActual = this.nocturna;
    }

    public void ejecutarFaseActual(List<Jugador> jugadores) {
        faseActual.ejecutar(jugadores);
    }
    public void avanzarFase(List<Jugador> jugadores) {}
    public boolean estaTerminado() {return false;}
}
