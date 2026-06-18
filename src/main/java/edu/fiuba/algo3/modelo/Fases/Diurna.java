package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Debate;
import edu.fiuba.algo3.modelo.Votacion;

import java.util.List;

public class Diurna implements Fase {

    private Debate debate;
    private Votacion votacion;

    public Diurna() {
    }

    @Override
    public void ejecutar(List<Jugador> jugadores,
                         RegistroNocturno registro) {

        Jugador eliminado = votacion.resolverVotacion();

        if (eliminado != null) {
            eliminado.eliminar();
        }
    }
}
