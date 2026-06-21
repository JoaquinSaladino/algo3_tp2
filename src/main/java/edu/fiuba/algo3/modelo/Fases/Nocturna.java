package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.AccionNocturna.*;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;

import java.util.ArrayList;
import java.util.List;

public class Nocturna implements Fase {
    private List<AccionNocturna> intencionesRecolectadas;


    @Override
    public void ejecutar(List<Jugador> jugadores, RegistroNocturno registroActual) {
        // Recolectar acciones de jugadores con habilidades
        intencionesRecolectadas = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            if (!jugador.estaVivo()) continue;

            Jugador objetivo = jugador.obtenerObjetivoElegido();
            if (objetivo != null && objetivo != jugador) {
                    AccionNocturna accion = jugador.usarHabilidad(objetivo);
                    if (accion != null) {
                        intencionesRecolectadas.add(accion);
                    }
            }
        }

        intencionesRecolectadas
                .forEach(accion -> accion.resolver(registroActual));

        Jugador victimaElegidaPorLaMafia = registroActual.obtenerMasVotadoPorLaMafia();
        if (victimaElegidaPorLaMafia != null && victimaElegidaPorLaMafia.estaVivo()) {
            AccionNocturna accionEliminar = new AEliminar(null, victimaElegidaPorLaMafia);
            accionEliminar.resolver(registroActual);
            victimaElegidaPorLaMafia.desproteger();
        }

        for (Jugador jugador : jugadores) {
            if (jugador.estaProtegido()) {
                jugador.desproteger();
            }
        }
    }

}


