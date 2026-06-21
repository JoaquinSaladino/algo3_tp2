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
        Jugador jugador1 = new Jugador("Pepe");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Leo");
        RolFactory rolFactory = new RolFactory();
        jugador1.asignarCarta(rolFactory.crearCartaMafioso());
        jugador2.asignarCarta(rolFactory.crearCartaCiudadano());
        jugador3.asignarCarta(rolFactory.crearCartaMedico());

        List<Jugador> jugadores  = List.of(jugador1,jugador2,jugador3);
        CondicionVictoria victoriaCiudadano = new CondicionVictoriaCiudadano();
        assertFalse(victoriaCiudadano.verificarCondicion(jugadores));
        jugador1.eliminar();
        assertTrue(victoriaCiudadano.verificarCondicion(jugadores));
    }

    @Test
    public void test02CuandoLosMafiososIgualanOSuperanALosCiudadanosGanan(){
        Jugador jugador1 = new Jugador("Pepe");
        Jugador jugador2 = new Jugador("Toño");
        Jugador jugador3 = new Jugador("Leo");
        RolFactory rolFactory = new RolFactory();
        jugador1.asignarCarta(rolFactory.crearCartaMafioso());
        jugador2.asignarCarta(rolFactory.crearCartaCiudadano());
        jugador3.asignarCarta(rolFactory.crearCartaMedico());

        List<Jugador> jugadores  = List.of(jugador1,jugador2,jugador3);
        CondicionVictoria victoriaMafia = new CondicionVictoriaMafia();
        assertFalse(victoriaMafia.verificarCondicion(jugadores));
        jugador2.eliminar();
        assertTrue(victoriaMafia.verificarCondicion(jugadores));
    }

}
