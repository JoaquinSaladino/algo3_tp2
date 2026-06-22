package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Fases.Diurna;
import edu.fiuba.algo3.modelo.Fases.Fase;
import edu.fiuba.algo3.modelo.Fases.Nocturna;

import java.util.List;

public class Turno {
    private final Nocturna nocturna;
    private final Diurna diurna;
    private Fase faseActual;
    private final RegistroNocturno registro;

    public Turno() {
        this(new Nocturna(), new Diurna(), new RegistroNocturno());
    }

    public Turno(Nocturna nocturna, Diurna diurna) {
        this(nocturna, diurna, new RegistroNocturno());
    }

    public Turno(Nocturna nocturna, Diurna diurna, RegistroNocturno registro) {
        this.nocturna = nocturna;
        this.diurna = diurna;
        this.faseActual = this.nocturna;
        this.registro = registro;
    }

    public void ejecutarFaseActual(List<Jugador> jugadores) {
        faseActual.ejecutar(jugadores , registro);
    }

    public void avanzarFase(List<Jugador> jugadores) {
        if (this.faseActual == this.nocturna) {
            this.faseActual = this.diurna;
        } else {
            this.faseActual = this.nocturna;
        }
    }

    public String getProximoJugador() {
        return "";
    }

//REVISAR (No se usa)
//    public boolean estaTerminado() {return false;}
}
