package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Debate;
import edu.fiuba.algo3.modelo.Desempate.SinEliminacion;
import edu.fiuba.algo3.modelo.Fases.Diurna;
import edu.fiuba.algo3.modelo.Fases.Fase;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.RolFactory;
import edu.fiuba.algo3.modelo.Votacion;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class FaseDiurnaTest {
    @Test
    public void test01VerificarQueLaVotacionDiurnaElimineAlJugadorConMayorCantidadDeVotos(){
        //Arrange
        Jugador jugador1 = new Jugador("Lalo");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Pepe");
        RolFactory rolFactory = new RolFactory();
        jugador1.asignarCarta(rolFactory.crearCartaMafioso());
        jugador2.asignarCarta(rolFactory.crearCartaCiudadano());
        jugador3.asignarCarta(rolFactory.crearCartaMedico());
        List<Jugador> jugadores = List.of(jugador1, jugador2,jugador3);
        RegistroNocturno registroNocturno = new RegistroNocturno();

        Votacion votacion = new Votacion(new SinEliminacion());

        //Act

        votacion.iniciar(jugadores);
        votacion.registrarVoto(jugador2, jugador1);
        votacion.registrarVoto(jugador3, jugador1);

        Diurna diurna = new Diurna(new Debate(), votacion);

        diurna.ejecutar(jugadores, registroNocturno);

        //Assert

        assertFalse(jugador1.estaVivo());
    }

    @Test
    public void test02VerificarElMecanismoDeEmpateConfigurado(){
        // Arrange
        Jugador jugador1 = new Jugador("Lalo");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Pepe");
        Jugador jugador4 = new Jugador("Marcelo");
        Jugador jugador5 = new Jugador("Luis");

        Votacion votacion = new Votacion(new SinEliminacion());

        votacion.iniciar(Arrays.asList(
                jugador1,
                jugador2,
                jugador3,
                jugador4,
                jugador5
        ));

        // Act
        votacion.registrarVoto(jugador1, jugador2);
        votacion.registrarVoto(jugador2, jugador4);
        votacion.registrarVoto(jugador3, jugador4);
        votacion.registrarVoto(jugador4, jugador2);
        votacion.registrarVoto(jugador5, jugador3);

        Jugador eliminado = votacion.resolverVotacion();

        // Assert
        assertNull(eliminado);
    }

    @Test
    public void test03VerificarQueLaCartaDelJugadorEliminadoQuedeReveladaParaTodosAlSerEliminado(){
        //Arrange
        Jugador jugador1 = new Jugador("Lalo");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Pepe");
        RolFactory rolFactory = new RolFactory();
        jugador1.asignarCarta(rolFactory.crearCartaMafioso());
        jugador2.asignarCarta(rolFactory.crearCartaCiudadano());
        jugador3.asignarCarta(rolFactory.crearCartaMedico());
        List<Jugador> jugadores = List.of(jugador1, jugador2,jugador3);
        Votacion votacion = new Votacion(new SinEliminacion());

        //Act
        votacion.iniciar(List.of(jugador1,jugador2,jugador3));
        votacion.registrarVoto(jugador2, jugador1);
        votacion.registrarVoto(jugador3, jugador1);
        Diurna diurna = new Diurna(new Debate(), votacion);
        diurna.ejecutar(jugadores, new RegistroNocturno());

        //Assert
        assertEquals("Mafioso", jugador1.verRol());

    }
}
