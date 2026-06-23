package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.AccionNocturna.*;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Nocturna implements Fase {
    private Iterator<Jugador> iterador;
    private Jugador jugadorActual;
    private List<AccionNocturna> intenciones;
    private String resumenFinal;

    public Nocturna() {
        this.intenciones = new ArrayList<>();
        this.resumenFinal = "";
    }

    public void iniciar(List<Jugador> jugadores) {
        this.iterador = jugadores.stream().filter(Jugador::estaVivo).iterator();
        avanzarJugador();
    }

    @Override
    public void ejecutar(List<Jugador> jugadores, RegistroNocturno registro) {
        intenciones.forEach(accion -> accion.resolver(registro));

        Jugador victimaDeLaMafia = registro.obtenerMasVotadoPorLaMafia();
        if (victimaDeLaMafia != null && victimaDeLaMafia.estaVivo()) {
            AccionNocturna accionEliminar = new AEliminar(null, victimaDeLaMafia);
            accionEliminar.resolver(registro);
            if(victimaDeLaMafia.estaVivo()){
                resumenFinal = "El Jugador" + victimaDeLaMafia.getNombre() + "fue atacado , pero estaba protegido";
            }else{
                resumenFinal = "El Jugador" + victimaDeLaMafia.getNombre() + "ha muerto";
            }
            victimaDeLaMafia.desproteger();
        }else{
            resumenFinal = "No hubo victima en la noche";
        }

        for (Jugador jugador : jugadores) {
            if (jugador.estaProtegido()) {
                jugador.desproteger();
            }
        }
    }

    @Override
    public boolean avanzarJugador() {
        if (iterador.hasNext()) {
            jugadorActual = iterador.next();
            return true;
        }
        return false;
    }

    @Override
    public List<String> obtenerObjetivosValidos(List<Jugador> jugadores) {
        return this.jugadorActual.obtenerObjetivosValidos(jugadores);
    }

    @Override
    public boolean seleccionarObjetivo(Jugador objetivo) {
        if(!this.jugadorActual.obtenerCarta().esObjetivoValido(this.jugadorActual, objetivo)) {
            return false;
        }
        AccionNocturna accion = this.jugadorActual.usarHabilidad(objetivo);
            if(accion != null) {
                intenciones.add(accion);
            }
            return true;
    }

    @Override
    public String obtenerResumenFase() {
        return resumenFinal;
    }

    @Override
    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public String getResumenFinal() {
        return resumenFinal;
    }
}


