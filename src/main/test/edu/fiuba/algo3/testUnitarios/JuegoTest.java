package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JuegoTest {
        @Test
        public void testIniciarJuegoCon5Jugadores(){
            Jugador jugador1 = new Jugador("J1");
            Jugador jugador2 = new Jugador("J2");
            Jugador jugador3 = new Jugador("J3");
            Jugador jugador4 = new Jugador("J4");
            Jugador jugador5 = new Jugador("J5");

            List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3, jugador4, jugador5);

            Juego juego = new Juego(jugadores);
            juego.iniciarPartida();

            assertEquals(5, juego.cantidadJugadores());
        }
}
