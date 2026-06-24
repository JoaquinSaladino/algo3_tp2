package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Desempate.SinEliminacion;
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
        this(new Nocturna(), new Diurna(new Debate(), new Votacion(new SinEliminacion())), new RegistroNocturno());
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

    public void iniciarFase(List<Jugador> jugadores) {
        this.faseActual.iniciar(jugadores);
    }

    public void ejecutarFaseActual(List<Jugador> jugadores) {
        faseActual.ejecutar(jugadores , registro);
    }

    public boolean avanzarFase(List<Jugador> jugadores) {
        if (this.faseActual == this.nocturna) {
            this.faseActual = this.diurna;
        } else {
            this.faseActual = this.nocturna;
            this.registro.limpiar();
        }
        this.faseActual.iniciar(jugadores);
        return true;
    }

    public String getJugadorActual() {
        return faseActual.getJugadorActual().getNombre();
    }

    public boolean avanzarJugador() {
        return this.faseActual.avanzarJugador();

    }
    public boolean seleccionarObjetivoNocturno( Jugador objetivo) {
        return faseActual.seleccionarObjetivo(objetivo);
    }
    public List<String> obtenerObjetivosValidos(List<Jugador> jugadores) {
        return faseActual.obtenerObjetivosValidos(jugadores);
    }
    public String obtenerResumenFase() {
        return faseActual.obtenerResumenFase();
    }

    public String getProximoJugador() {
        return "";
    }

    public boolean estaEnNominacion()
    {
        return faseActual.estaEnNominacion();
    }
    //REVISAR (No se usa)
//    public boolean estaTerminado() {return false;}
}
