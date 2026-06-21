package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Configuracion.ConfiguracionPartida;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Juego {
    private List<Jugador> jugadores;
    private Turno turnoActual;
    private Mazo mazo;

    public Juego(int cantidadJugadores) {
        this.turnoActual = new Turno();
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
        Optional<Jugador> jugadorActual = jugadores.stream()
                .filter(jugador -> jugador.esMismoNombre(nombre))
                .findFirst();

        FiltroObjetivos filtro = new FiltroObjetivos();
        //Falta Terminar para que el jugador actual pase con el resto de jugadores por un filtro en base al rol.
        return jugadorActual;
    }

    public void seleccionarObjetivo(String nombreJugadorActual){

    }

    public void nominarObjetivo(String nombreJugadorActual){

    }

    public List<String> obtenerNominados (){
        return ;
    }
    public void votarObjetivo(String nombreJugadorActual){

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
