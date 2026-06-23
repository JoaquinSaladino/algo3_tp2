package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.CondicionVictoria.CondicionVictoria;
import edu.fiuba.algo3.modelo.CondicionVictoria.CondicionVictoriaCiudadano;
import edu.fiuba.algo3.modelo.CondicionVictoria.CondicionVictoriaMafia;
import edu.fiuba.algo3.modelo.Roles.RolFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CondicionVictoriaTest {
    @Test
    public void test01CuandoTodosLosMafiososMuerenGananLosCiudadanos(){
        //Arrange
        Jugador jugador1 = new Jugador("Pepe");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Leo");
        RolFactory rolFactory = new RolFactory();
        jugador1.asignarCarta(rolFactory.crearCartaMafioso());
        jugador2.asignarCarta(rolFactory.crearCartaCiudadano());
        jugador3.asignarCarta(rolFactory.crearCartaMedico());
        //Act
        List<Jugador> jugadores  = List.of(jugador1,jugador2,jugador3);
        CondicionVictoria victoriaCiudadano = new CondicionVictoriaCiudadano();
        //Assert
        assertFalse(victoriaCiudadano.verificarCondicion(jugadores));
        jugador1.eliminar();
        assertTrue(victoriaCiudadano.verificarCondicion(jugadores));
    }

    @Test
    public void test02CuandoLosMafiososIgualanOSuperanALosCiudadanosGanan(){
        //Arrange
        Jugador jugador1 = new Jugador("Pepe");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Leo");
        RolFactory rolFactory = new RolFactory();
        jugador1.asignarCarta(rolFactory.crearCartaMafioso());
        jugador2.asignarCarta(rolFactory.crearCartaCiudadano());
        jugador3.asignarCarta(rolFactory.crearCartaMedico());
        //Act
        List<Jugador> jugadores  = List.of(jugador1,jugador2,jugador3);
        CondicionVictoria victoriaMafia = new CondicionVictoriaMafia();
        //Assert
        assertFalse(victoriaMafia.verificarCondicion(jugadores));
        jugador2.eliminar();
        assertTrue(victoriaMafia.verificarCondicion(jugadores));
    }

    @Test
    public void test03ElJuegoTerminaCuandoGananLosCiudadanos() {
        //Arrange
        Juego juego = new Juego();

        Jugador jugador1 = new Jugador("Pepe");
        Jugador jugador2 = new Jugador("Juan");
        Jugador jugador3 = new Jugador("Lolo");
        RolFactory factory = new RolFactory();
        jugador1.asignarCarta(factory.crearCartaMafioso());
        jugador2.asignarCarta(factory.crearCartaCiudadano());
        jugador3.asignarCarta(factory.crearCartaCiudadano());
        //Act
        List<Jugador> jugadores  = List.of(jugador1,jugador2,jugador3);
        CondicionVictoria victoriaCiudadano = new CondicionVictoriaCiudadano();
        jugador1.eliminar();
        juego.setJugadores(jugadores);
        //Assert
        assertTrue(victoriaCiudadano.verificarCondicion(jugadores));
        assertTrue(juego.ciudadanosGanaron());
        assertTrue(juego.juegoTerminado());
    }

    @Test
    public void test04ElJuegoTerminaCuandoGanaLaMafia() {
        //Arrange
        Juego juego = new Juego();

        Jugador jugador1 = new Jugador("Maria");
        Jugador jugador2 = new Jugador("Carlos");
        Jugador jugador3 = new Jugador("Luiz");
        Jugador jugador4 = new Jugador("Pedro");
        Jugador jugador5 = new Jugador("Marcos");
        RolFactory factory = new RolFactory();
        jugador1.asignarCarta(factory.crearCartaMafioso());
        jugador2.asignarCarta(factory.crearCartaCiudadano());
        jugador3.asignarCarta(factory.crearCartaMedico());
        jugador4.asignarCarta(factory.crearCartaMafioso());
        jugador5.asignarCarta(factory.crearCartaDetective());
        //Act
        List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3, jugador4, jugador5);
        CondicionVictoriaMafia victoriaMafia = new CondicionVictoriaMafia();
        jugador2.eliminar();
        juego.setJugadores(jugadores);
        //Assert
        assertTrue(victoriaMafia.verificarCondicion(jugadores));
        assertTrue(juego.mafiaGano());
        assertTrue(juego.juegoTerminado());
    }

}
