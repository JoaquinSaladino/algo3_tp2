package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JuegoTest {
    //Arrange
    //Act
    //Assert
    @Test
    public void test01JuegoSeConfiguraCon5JugadoresCadaUnoConUnRolAsignado(){
        //Arrange
        List<String> jugadores = List.of("jugador1", "jugador2", "jugador3", "jugador4", "jugador5");
        Juego juego = new Juego();
        //Act
        juego.configurarPartida(jugadores);
        //Assert
        assertEquals(5, juego.cantidadJugadores());
        for (String nombre : jugadores) {
            Jugador jugador = juego.buscarJugadorPorNombre(nombre);
            assertNotNull(jugador.obtenerCarta());
        }
    }

    @Test
    public void testDeIntegracion() {
        // Arrange: Configuramos el juego con 5 jugadores
        Juego juego = new Juego();
        List<String> nombres = List.of("jugador1", "jugador2", "jugador3", "jugador4", "jugador5");

        juego.configurarPartida(nombres);
        juego.iniciarPartida();

        // Act & Assert
        // 1. Verificamos que el turno inicie con el primer jugador
        String jugadorActual = juego.getJugadorActual();
        assertEquals("jugador1", jugadorActual);

        // 2. Obtenemos el rol (esto valida que se haya repartido correctamente)
        String rol = juego.getRolJugadorActual(jugadorActual);
        assertNotNull(rol);

        // 3. Obtenemos objetivos nocturnos (la lógica del modelo filtrará según el rol automáticamente)
        List<String> objetivos = juego.obtenerObjetivos();

        // 4. Seleccionamos un objetivo (si el jugador puede hacerlo)
        // Nota: Como no sabemos si el rol tiene habilidad nocturna o si es válido,
        // controlamos el flujo con la lista obtenida
        if (!objetivos.isEmpty()) {
            String objetivoElegido = objetivos.get(0);
            boolean resultado = juego.seleccionarObjetivo( objetivoElegido);

            // Verificamos que la acción fue registrada
            assertTrue(resultado);
        }
        juego.avanzarJugadorActual();
        String jugadorActual2 = juego.getJugadorActual();
        assertNotSame(jugadorActual2, jugadorActual);
        assertEquals("jugador2", jugadorActual2);
        // 5. Avanzamos fase para comprobar la integración del Turno
        juego.ejecutarFaseActual();
        juego.avanzarFase();

        // Si el juego terminara por alguna condición inicial (poco probable pero posible en TDD),
        // podríamos verificarlo aquí:
        // assertFalse(juego.juegoTerminado());
    }

}
