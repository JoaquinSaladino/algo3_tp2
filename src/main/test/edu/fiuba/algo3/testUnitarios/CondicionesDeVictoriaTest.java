package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Medico;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CondicionesDeVictoriaTest {
    @Test
    public void test01CiudadanosGananCuandoNoQuedanMafiosos() {

        Jugador ciudadano = new Jugador("Juan");
        ciudadano.asignarCarta(new Ciudadano());

        Jugador medico = new Jugador("Pedro");
        medico.asignarCarta(new Medico());

        Jugador mafioso = new Jugador("Carlos");
        mafioso.asignarCarta(new Mafioso());

        mafioso.eliminar();

        Juego juego = new Juego(
                List.of(ciudadano, medico, mafioso)
        );

        assertTrue(juego.ciudadanosGanaron());
    }

    @Test
    public void test02MafiaGanaCuandoIgualaACiudadanos() {

        Jugador mafioso = new Jugador("Carlos");
        mafioso.asignarCarta(new Mafioso());

        Jugador ciudadano = new Jugador("Juan");
        ciudadano.asignarCarta(new Ciudadano());

        Juego juego = new Juego(
                List.of(mafioso, ciudadano)
        );

        assertTrue(juego.mafiaGano());
    }

    @Test
    public void test03MafiaGanaCuandoSuperaACiudadanos() {

        Jugador mafioso1 = new Jugador("M1");
        mafioso1.asignarCarta(new Mafioso());

        Jugador mafioso2 = new Jugador("M2");
        mafioso2.asignarCarta(new Mafioso());

        Jugador ciudadano = new Jugador("C1");
        ciudadano.asignarCarta(new Ciudadano());

        Juego juego = new Juego(
                List.of(mafioso1, mafioso2, ciudadano)
        );

        assertTrue(juego.mafiaGano());
    }

    @Test
    public void test04JuegoNoTerminoMientrasExistanMafiososYCiudadanos() {

        Jugador mafioso = new Jugador("M1");
        mafioso.asignarCarta(new Mafioso());

        Jugador ciudadano1 = new Jugador("C1");
        ciudadano1.asignarCarta(new Ciudadano());

        Jugador ciudadano2 = new Jugador("C2");
        ciudadano2.asignarCarta(new Ciudadano());

        Juego juego = new Juego(
                List.of(mafioso, ciudadano1, ciudadano2)
        );

        assertFalse(juego.juegoTerminado());
    }
}
