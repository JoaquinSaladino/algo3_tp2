package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Configuracion.ConfiguracionPartida;
import edu.fiuba.algo3.modelo.Roles.CartaRol;
import edu.fiuba.algo3.modelo.Turno;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Juego {
    private List<Jugador> jugadores;
    private Turno turnoActual;
    private Mazo mazo;
    private Debate debate;

    public Juego(int cantidadJugadores) {
        this.turnoActual = new Turno();
        this.debate = new Debate();
    }

    public void configurarPartida(List<String> nombresJugadores){
        int cantidadJugadores = nombresJugadores.size();
        this.mazo = new ConfiguracionPartida(cantidadJugadores).generarMazo();
        this.mazo.mezclar();
        crearJugadores(nombresJugadores);
    }

    private void crearJugadores(List<String> nombresJugadores){
        this.jugadores = new ArrayList<>();
        nombresJugadores.forEach( nombreJugador -> jugadores.add( crearJugador(nombreJugador) ));
    }

    private Jugador crearJugador(String nombre){
        CartaRol carta= mazo.repartir();
        Jugador instanciaJugador = new Jugador(nombre);
        instanciaJugador.asignarCarta(carta);
        return instanciaJugador;
    }

    public void iniciarPartida(){

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

    public String getProximoJugador(){
        return turnoActual.getProximoJugador();
    }

    public List<String> obtenerObjetivosValidos(String nombreJugadorActual){
        Optional<Jugador> jugadorActual =
                jugadores.stream()
                        .filter(jugador -> jugador.esMismoNombre(nombreJugadorActual))
                        .findFirst();
        if (jugadorActual.isEmpty()) return List.of();
        FiltroObjetivos filtro = new FiltroObjetivos();
        return filtro
                .filtrar(jugadorActual.get(), jugadores)
                .stream()
                .map(Jugador::toString)
                .collect(Collectors.toList());
    }

    public void seleccionarObjetivo(String nombreJugadorActual, String nombreObjetivo){
        Jugador actual = jugadores.stream()
                .filter(j -> j.esMismoNombre(
                        nombreJugadorActual))
                .findFirst()
                .orElseThrow();

        Jugador objetivo = jugadores.stream()
                .filter(j -> j.esMismoNombre(
                        nombreObjetivo))
                .findFirst()
                .orElseThrow();

        actual.seleccionarObjetivo(objetivo);
    }

    private Jugador buscarJugador(String nombre) {
        return jugadores.stream()
                .filter(j -> j.esMismoNombre(nombre))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No existe el jugador: " + nombre
                        )
                );
    }

    public void nominarObjetivo(String nominador, String nominado){
        Jugador jugadorNominador = buscarJugador(nominador);
        Jugador jugadorNominado = buscarJugador(nominado);
        debate.nominar(jugadorNominador, jugadorNominado);
    }

    public List<String> obtenerNominados(){
        return debate.getNominados()
                .stream()
                .map(Jugador::obtenerNombre)
                .collect(Collectors.toList());
    }

    public void votarObjetivo(String votante, String nominado){}

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

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
