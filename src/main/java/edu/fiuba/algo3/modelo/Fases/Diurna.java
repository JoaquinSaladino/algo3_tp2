package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Debate;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Votacion;
import java.util.*;
import edu.fiuba.algo3.modelo.Debate;
import edu.fiuba.algo3.modelo.Votacion;

import java.util.List;
import java.util.stream.Collectors;
public class Diurna implements Fase {
    private Iterator<Jugador> iterador;
    private Jugador jugadorActual;
    private Debate debate;
    private Votacion votacion;
    private String resumenFinal;

    public Diurna() {
    }

    public Diurna(Debate debate, Votacion votacion) {
        this.debate = debate;
        this.votacion = votacion;
        this.resumenFinal = "";
    }

    @Override
    public void iniciar(List<Jugador> jugadores) {
        this.iterador = jugadores.stream().filter(Jugador::estaVivo).iterator();
        avanzarJugador();
    }

    @Override
    public void ejecutar(List<Jugador> jugadores,
                         RegistroNocturno registro) {
        List<Jugador> jugadoresVivos = jugadores.stream()
                .filter(jugador -> jugador.estaVivo())
                .collect(Collectors.toList());

    //Revelar Sherif?
        debate.iniciar(jugadoresVivos);

        Jugador eliminado = votacion.resolverVotacion();

        if (eliminado != null) {
            eliminado.eliminar();
            resumenFinal = "El jugador " + eliminado.getNombre() + " ha sido eliminado.";
        } else {
            resumenFinal = "No hubo eliminaciones en el dia.";
        }
    }

    @Override
    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    @Override
    public boolean avanzarJugador() {
        if (iterador.hasNext()) {
            jugadorActual = iterador.next();
            return true;
        }
        return false;    }

    @Override
    public List<String> obtenerObjetivosValidos(List<Jugador> jugadores) {
        List<String> jugadoresValidos = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            if(jugador!=jugadorActual && jugador.estaVivo()) {
                jugadoresValidos.add(jugador.getNombre());
            }
        }
        return jugadoresValidos;
    }

    @Override
    public boolean seleccionarObjetivo( Jugador objetivo) {
        if(objetivo == jugadorActual || !objetivo.estaVivo()) {
            return false;
        } else {
            votacion.registrarVoto(jugadorActual, objetivo);
            return true;
        }
    }

    @Override
    public String obtenerResumenFase() {
        return resumenFinal;
    }
}
