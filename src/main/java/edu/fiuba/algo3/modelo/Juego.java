package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Configuracion.ConfiguracionPartida;
import java.util.List;
import java.util.stream.Collectors;

public class Juego {
    private final List<Jugador> jugadores;
    private Turno turnoActual;
    private Mazo mazo;

    public Juego(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.turnoActual = new Turno();
    }

    public void iniciarPartida(){
        this.mazo = new ConfiguracionPartida(cantidadJugadores()).generarMazo();
        this.mazo.mezclar();

        for (Jugador jugador : jugadores) {
            jugador.asignarCarta(this.mazo.repartir());
        }

        var mafiosos = jugadores.stream()
                .filter(Jugador::esMafia)
                .collect(Collectors.toList());

        for (Jugador mafioso : mafiosos) {
            var companeros = mafiosos.stream()
                    .filter(jugador -> jugador != mafioso)
                    .collect(Collectors.toList());
            mafioso.registrarCompaneros(companeros);
        }
    }

    public int cantidadJugadores(){
        return jugadores.size();
    }

    public void ejecutarFaseActual(){
        turnoActual.ejecutarFaseActual(jugadores);
    }

    public void avanzarFase(){
        turnoActual.avanzarFase(jugadores);
    }

    public boolean mafiaGano() {
        long mafiosos = jugadores.stream()
                .filter(Jugador::estaVivo)
                .filter(Jugador::esMafia)
                .count();

        long ciudadanos = jugadores.stream()
                .filter(Jugador::estaVivo)
                .filter(j -> !j.esMafia())
                .count();

        return mafiosos >= ciudadanos;
    }

    public boolean ciudadanosGanaron() {
        return jugadores.stream()
                .filter(Jugador::estaVivo)
                .noneMatch(Jugador::esMafia);
    }

    public boolean juegoTerminado() {
        return mafiaGano() || ciudadanosGanaron();
    }
}
