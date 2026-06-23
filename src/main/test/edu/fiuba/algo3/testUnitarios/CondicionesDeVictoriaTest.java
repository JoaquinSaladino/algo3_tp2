package edu.fiuba.algo3.testUnitarios;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Roles.CartaRol;
import edu.fiuba.algo3.modelo.Roles.RolFactory;
import org.junit.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CondicionesDeVictoriaTest {

    private RolFactory factory = new RolFactory();
    @Test
    public void test01CiudadanosGananCuandoNoQuedanMafiosos() {
        Juego juego = new Juego();
        juego.configurarPartida(List.of("Juan", "Pedro", "Carlos"));
        juego.iniciarPartida();

        juego.buscarJugadorPorNombre("Juan").eliminar();

        assertTrue(juego.ciudadanosGanaron());
    }

    @Test
    public void test02MafiaGanaCuandoIgualaACiudadanos() {
        Juego juego = new Juego();
        juego.configurarPartida(List.of("Carlos", "Juan"));
        juego.iniciarPartida();
        assertTrue(juego.mafiaGano());
    }

    @Test
    public void test03MafiaGanaCuandoSuperaACiudadanos() {
        Juego juego = new Juego();
        juego.configurarPartida(List.of("M1", "M2", "C1"));
        juego.iniciarPartida();

        assertTrue(juego.mafiaGano());
    }

    @Test
    public void test04JuegoNoTerminoMientrasExistanMafiososYCiudadanos() {
        Juego juego = new Juego();
        juego.configurarPartida(List.of("M1", "C1", "C2"));
        juego.iniciarPartida();

        assertFalse(juego.mafiaGano());
        assertFalse(juego.ciudadanosGanaron());
    }
}