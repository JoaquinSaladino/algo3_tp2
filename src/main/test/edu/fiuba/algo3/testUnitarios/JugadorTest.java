package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Configuracion.BalanceoJuegoChico;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Excepciones.RolNoVisibleException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Detective;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void test01CadaJugadorRecibeExactamenteUnRol()
    {
        // Arrange
        List<Jugador> jugadores = new ArrayList<>();

        jugadores.add(new Jugador("J1"));
        jugadores.add(new Jugador("J2"));
        jugadores.add(new Jugador("J3"));
        jugadores.add(new Jugador("J4"));
        jugadores.add(new Jugador("J5"));

        Mazo mazo = new BalanceoJuegoChico().crearMazo(5);

        // Act
        for (Jugador jugador : jugadores)
        {
            jugador.asignarCarta(mazo.repartir());
        }

        // Assert
        for (Jugador jugador : jugadores)
        {
            assertNotNull(jugador.obtenerCarta());
        }

        assertTrue(mazo.estaVacio());
    }

    @Test
    public void test02CadaJugadorVeSuPropioRol()
    {
        // Arrange
        Jugador jugador1 = new Jugador("J1");

        Ciudadano detective = new Detective();

        jugador1.asignarCarta(detective);

        // Assert
        assertEquals(detective.obtenerRol(), jugador1.verRol());
    }

    @Test
    public void test03UnJugadorNoPuedeVerElRolDeOtroJugador()
    {
        // Arrange
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        // Act & Assert
        assertThrows(
                RolNoVisibleException.class,
                () -> jugador1.verCartaDe(jugador2)
        );
    }

    @Test
    public void test04UnMafiosoConoceAOtrosMafiososSiSeLosRegistran() {
        // Arrange
        Jugador mafioso1 = new Jugador("J1");
        Jugador mafioso2 = new Jugador("J2");
        Jugador ciudadano = new Jugador("J3");

        mafioso1.asignarCarta(new Mafioso());
        mafioso2.asignarCarta(new Mafioso());
        ciudadano.asignarCarta(new Ciudadano());

        mafioso1.registrarCompaneros(List.of(mafioso2));

        // Assert
        assertTrue(mafioso1.conoceA(mafioso2), "El mafioso debería conocer a su cómplice");
        assertFalse(mafioso1.conoceA(ciudadano), "El mafioso no debería conocer al ciudadano");
    }

    @Test
    public void test05UnCiudadanoComunNoConoceANadie() {

        // Arrange
        Jugador ciudadano = new Jugador("J1");
        Jugador mafioso = new Jugador("J2");

        ciudadano.asignarCarta(new Ciudadano());
        mafioso.asignarCarta(new Mafioso());

        // Assert
        assertFalse(ciudadano.conoceA(mafioso), "El ciudadano no debería conocer a nadie");
    }

    @Test
    public void test06LaMafiaNoPuedeSeleccionarOtroMafioso()
    {
        // Arrange
        Jugador mafioso = new Jugador("Mafioso");
        mafioso.asignarCarta(new Mafioso());

        // Act & Assert
        assertThrows(
                ObjetivoInvalidoException.class,
                () -> mafioso.usarHabilidad(mafioso)
        );
    }
}
