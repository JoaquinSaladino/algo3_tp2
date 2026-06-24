package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Configuracion.ConfiguracionPartida;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Juego {
    private List<Jugador> jugadores;
    private Turno turnoActual;
    private Mazo mazo;
    private Debate debate;

    public Juego() {
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
        turnoActual.iniciarFase(jugadores);
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

    public boolean avanzarJugadorActual(){
       return turnoActual.avanzarJugador();
    }

    public String getJugadorActual(){
        return turnoActual.getJugadorActual();
    }

    public String getRolJugadorActual(String nombre){
        return buscarJugadorPorNombre(nombre).verRol();
    }

    public Jugador buscarJugadorPorNombre(String nombre) {
        return this.jugadores.stream()
                .filter(j -> j.esMismoNombre(nombre))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado: " + nombre));
    }

    public List<String> obtenerObjetivos(){
        return this.turnoActual.obtenerObjetivosValidos(jugadores);
    }

    public boolean seleccionarObjetivo( String nombreObjetivo){
        Jugador objetivo = buscarJugadorPorNombre(nombreObjetivo);
        return turnoActual.seleccionarObjetivoNocturno(objetivo);
    }

    public int cantidadJugadores(){
        return jugadores.size();
    }

    public void ejecutarFaseActual(){

        this.turnoActual.ejecutarFaseActual(jugadores);
    }

    public String obtenerResultadoFase(){

        return turnoActual.obtenerResumenFase();
    }

    public boolean avanzarFase(){
        return this.turnoActual.avanzarFase(jugadores);
    }

    public void avanzarTurno(){
        this.turnoActual = new Turno();
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

    public void setJugadores(List<Jugador> jugadores){
        this.jugadores = jugadores;
    }

    public List<Jugador> getJugadores()
    {
        return this.jugadores;
    }

    public List<String> obtenerCompanerosJugadorActual() {
        String nombre = turnoActual.getJugadorActual();
        Jugador actual = buscarJugadorPorNombre(nombre);
        return actual.obtenerCompaneros();
    }

    public String obtenerResultadoInvestigacion(String detective, String objetivo) {
        return buscarJugadorPorNombre(objetivo).obtenerCarta().investigar();
    }

    public boolean estaEnNominacion()
    {
        return turnoActual.estaEnNominacion();
    }
}
