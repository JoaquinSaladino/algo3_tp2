package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Roles.RolFactory;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void testDeIntegracionSimulandoLaFaseNocturnaDe5Jugadores() {
        // Arrange: Configuramos el juego con 5 jugadores
        Juego juego = new Juego();
        RolFactory rolFactory = new RolFactory();
        Jugador jugador1 = new Jugador("Pepe");
        jugador1.asignarCarta(rolFactory.crearCartaCiudadano());

        Jugador jugador2 = new Jugador("Carlos");
        jugador2.asignarCarta(rolFactory.crearCartaMafioso());

        Jugador jugador3 = new Jugador("Leo");
        jugador3.asignarCarta(rolFactory.crearCartaCiudadano());

        Jugador jugador4 = new Jugador("Manfred");
        jugador4.asignarCarta(rolFactory.crearCartaMedico());

        Jugador jugador5 = new Jugador("Ricardo");
        jugador5.asignarCarta(rolFactory.crearCartaCiudadano());

        juego.setJugadores(List.of(jugador1,jugador2,jugador3,jugador4,jugador5));
        juego.iniciarPartida();

        // Act & Assert
        // Jugador 1 CIUDADANO
        String jugadorActual = juego.getJugadorActual();
        assertEquals("Pepe", jugadorActual);
        String rol = juego.getRolJugadorActual(jugadorActual);
        assertNotNull(rol);
        List<String> objetivosJugador1 = juego.obtenerObjetivos();
        assertTrue(objetivosJugador1.isEmpty());
        // Jugador2 MAFIOSO
        assertTrue (juego.avanzarJugadorActual());
        String jugadorActual2 = juego.getJugadorActual();
        assertNotSame(jugadorActual2, jugadorActual);
        assertEquals("Carlos", jugadorActual2);
        List<String> objetivosJugador2 = juego.obtenerObjetivos();
        assertFalse(objetivosJugador2.isEmpty());
        assertTrue(juego.seleccionarObjetivo(objetivosJugador2.get(0)));
        // Jugador3 CIUDADANO
        assertTrue (juego.avanzarJugadorActual());
        String jugadorActual3 = juego.getJugadorActual();
        assertNotSame(jugadorActual3, jugadorActual2);
        assertEquals("Leo", jugadorActual3);
        List<String> objetivosJugador3 = juego.obtenerObjetivos();
        assertTrue(objetivosJugador3.isEmpty());
        // Jugador4 MÉDICO
        assertTrue (juego.avanzarJugadorActual());
        String jugadorActual4 = juego.getJugadorActual();
        assertNotSame(jugadorActual4, jugadorActual3);
        assertEquals("Manfred", jugadorActual4);
        List<String> objetivosJugador4 = juego.obtenerObjetivos();
        assertFalse(objetivosJugador4.isEmpty());
        assertTrue(juego.seleccionarObjetivo(objetivosJugador4.get(0)));

        //Jugador5 CIUDADANO
        assertTrue (juego.avanzarJugadorActual());
        String jugadorActual5 = juego.getJugadorActual();
        assertNotSame(jugadorActual5, jugadorActual2);
        assertEquals("Ricardo", jugadorActual5);
        List<String> objetivosJugador5 = juego.obtenerObjetivos();
        assertTrue(objetivosJugador5.isEmpty());
        // Fin fase nocturna se obtiene Resumen de que paso a la noche.
        assertFalse(juego.avanzarJugadorActual());
        juego.ejecutarFaseActual();
        String resumen = juego.obtenerResultadoFase();
        System.out.println(resumen);
        assertTrue(juego.avanzarFase());
    }

    @Test public void testDeIntegracionSimulandoLaFaseDiurnaDe5jugadores(){
        // Arrange: Configuramos el juego con 5 jugadores
        Juego juego = new Juego();
        RolFactory rolFactory = new RolFactory();
        Jugador jugador1 = new Jugador("Pepe");
        jugador1.asignarCarta(rolFactory.crearCartaCiudadano());

        Jugador jugador2 = new Jugador("Carlos");
        jugador2.asignarCarta(rolFactory.crearCartaMafioso());

        Jugador jugador3 = new Jugador("Leo");
        jugador3.asignarCarta(rolFactory.crearCartaCiudadano());

        Jugador jugador4 = new Jugador("Manfred");
        jugador4.asignarCarta(rolFactory.crearCartaMedico());

        Jugador jugador5 = new Jugador("Ricardo");
        jugador5.asignarCarta(rolFactory.crearCartaCiudadano());

        juego.setJugadores(List.of(jugador1,jugador2,jugador3,jugador4,jugador5));
        juego.iniciarPartida();

        juego.ejecutarFaseActual();
        System.out.println(juego.obtenerResultadoFase());
        juego.avanzarFase();

        //Jugador 1
        String jugadorActual1 = juego.getJugadorActual();
        assertEquals("Pepe", jugadorActual1);

        List<String> objetivosJugador1 = juego.obtenerObjetivos();
        assertFalse(objetivosJugador1.isEmpty());

    }

}
