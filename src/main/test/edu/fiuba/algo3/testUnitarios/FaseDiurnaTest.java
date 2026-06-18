package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Fases.Diurna;
import edu.fiuba.algo3.modelo.Fases.Fase;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Medico;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FaseDiurnaTest {
    @Test
    public void test01VerificarQueLaVotacionDiurnaElimineAlJugadorConMayorCantidadDeVotos(){
        //Arrange
        Jugador jugador1 = new Jugador("Lalo");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Pepe");
        Fase diurna = new Diurna();
        RegistroNocturno registroNocturno = new RegistroNocturno();
        jugador1.asignarCarta(new Mafioso());
        jugador2.asignarCarta(new Ciudadano());
        jugador3.asignarCarta(new Medico());

        Jugador jugador1Spy = Mockito.spy(jugador1);
        Jugador jugador2Spy = Mockito.spy(jugador2);
        Jugador jugador3Spy = Mockito.spy(jugador3);
        //Configuracion De Respuesta De Los Spy para simular seleccion.

        List<Jugador> jugadores = List.of(jugador1Spy, jugador2Spy,jugador3Spy);
        // Act
        diurna.ejecutar(jugadores,registroNocturno);
        //Assert
        assertFalse(jugador1Spy.estaVivo());


    }
    @Test
    public void test02VerificarQueElMecanismoDeEmpateConfigurado(){

    }

    @Test
    public void test03VerificarQueLaCartaDelJugadorEliminadoQuedeReveladaParaTodosAlSerEliminado(){
        //Arrange
        Jugador jugador1 = new Jugador("Lalo");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Pepe");
        Fase diurna = new Diurna();
        RegistroNocturno registroNocturno = new RegistroNocturno();
        jugador1.asignarCarta(new Mafioso());
        jugador2.asignarCarta(new Ciudadano());
        jugador3.asignarCarta(new Medico());

        Jugador jugador1Spy = Mockito.spy(jugador1);
        Jugador jugador2Spy = Mockito.spy(jugador2);
        Jugador jugador3Spy = Mockito.spy(jugador3);
        //Configuracion De Respuesta De Los Spy para simular seleccion.

        List<Jugador> jugadores = List.of(jugador1Spy, jugador2Spy,jugador3Spy);
        // Act
        diurna.ejecutar(jugadores,registroNocturno);
        //Assert
        verify(jugador1Spy).revelarRol();


    }
}
