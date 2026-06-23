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
    private boolean estadoNominacion;
    private ListIterator<Jugador> iterador;
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
        this.estadoNominacion = true;
    }

    @Override
    public void iniciar(List<Jugador> jugadores) {
        this.iterador = jugadores.stream()
                .filter(Jugador::estaVivo)
                .collect(Collectors.toList()).listIterator();
        avanzarJugador();
    }

    @Override
    public void ejecutar(List<Jugador> jugadores,
                         RegistroNocturno registro) {

        Jugador eliminado = votacion.resolverVotacion();

        if (eliminado != null) {
            eliminado.eliminar();
            resumenFinal = "El jugador " + eliminado.getNombre() + " ha sido eliminado.\n "+"Era " + eliminado.verRol() ;
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
        if (this.iterador.hasNext()) {
            this.jugadorActual = this.iterador.next();
            return true;
        }else{
            if (this.estadoNominacion) {
                this.estadoNominacion = false;
                this.reiniciarIterator();
                this.votacion.iniciar(this.debate.getNominados());
                return true;
            }
            return false;
        }
    }

    @Override
    public List<String> obtenerObjetivosValidos(List<Jugador> jugadores) {
        if(this.estadoNominacion){
            return this.debate.obtenerObjetivosValidos(jugadorActual, jugadores);
        }else{
            return  this.votacion.obtenerObjetivosValidos();
        }

    }

    @Override
    public boolean seleccionarObjetivo( Jugador objetivo) {
        if(objetivo == this.jugadorActual || !objetivo.estaVivo()) {
            return false;
        } else {
            if (this.estadoNominacion) {
                this.debate.nominar(objetivo);
                return true;
            } else {
                votacion.registrarVoto(this.jugadorActual, objetivo);
                return true;
            }
        }
    }

    @Override
    public String obtenerResumenFase() {
        return resumenFinal;
    }

    private void reiniciarIterator(){
        while (iterador.hasPrevious()){
            iterador.previous();
        }
    }
}
