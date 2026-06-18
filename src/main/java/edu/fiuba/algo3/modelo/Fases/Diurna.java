package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Debate;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Votacion;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
public class Diurna implements Fase {

    private Debate debate;
    private Votacion votacion;

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
        }
    }
}
