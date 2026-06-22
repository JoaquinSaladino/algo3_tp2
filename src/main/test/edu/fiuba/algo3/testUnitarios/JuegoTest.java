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
            //Arrange & Act
            List<String> jugadores = List.of("jugador1", "jugador2", "jugador3", "jugador4", "jugador5");

            Juego juego = new Juego(5);
            juego.configurarPartida(jugadores);

            //Assert
            assertEquals(5, juego.cantidadJugadores());
        }
}
