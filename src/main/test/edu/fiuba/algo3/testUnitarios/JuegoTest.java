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
            Jugador jugador1 = Mockito.mock(Jugador.class);
            Jugador jugador2 = Mockito.mock(Jugador.class);
            Jugador jugador3 = Mockito.mock(Jugador.class);
            Jugador jugador4 = Mockito.mock(Jugador.class);
            Jugador jugador5 = Mockito.mock(Jugador.class);

            List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3, jugador4, jugador5);

            Juego juego = new Juego(jugadores);
            juego.iniciarPartida();

            assertEquals(5, juego.cantidadJugadores());
        }
}
