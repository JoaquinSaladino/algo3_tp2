package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Configuracion.BalanceoJuegoChico;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Excepciones.RolNoVisibleException;
import edu.fiuba.algo3.modelo.Fases.Nocturna;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.CartaRol;
import edu.fiuba.algo3.modelo.Roles.RolFactory;
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
        RolFactory rolFactory = new RolFactory();
        CartaRol detective = rolFactory.crearCartaDetective();

        jugador1.asignarCarta(detective);

        // Assert
        assertEquals(detective.getRol(), jugador1.verRol());
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
        RolFactory rolFactory = new RolFactory();
        mafioso1.asignarCarta(rolFactory.crearCartaMafioso());
        mafioso2.asignarCarta(rolFactory.crearCartaMafioso());
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());

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
        RolFactory rolFactory = new RolFactory();
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());

        // Assert
        assertFalse(ciudadano.conoceA(mafioso), "El ciudadano no debería conocer a nadie");
    }

    @Test
    public void test06LaMafiaNoPuedeSeleccionarOtroMafioso()
    {
        // Arrange
        RolFactory rolFactory = new RolFactory();

        Jugador mafioso1 = new Jugador("Mafioso");
        mafioso1.asignarCarta(rolFactory.crearCartaMafioso());

        Jugador mafioso2 = new Jugador("Mafioso");
        mafioso2.asignarCarta(rolFactory.crearCartaMafioso());
        // Act & Assert
        CartaRol carta = rolFactory.crearCartaMafioso();

        //Act Assert
        assertFalse(carta.esObjetivoValido(mafioso1, mafioso2));
    }

    @Test
    public void test07CartaDeJugadorEliminadoEsRevelada(){
        // Arrange
        RolFactory rolFactory = new RolFactory();
        Jugador mafioso = new Jugador("Mafioso");
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());

        Jugador ciudadano = new Jugador("Ciudadano");
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());

        // Act
        mafioso.eliminar();

        //Assert
        assertEquals("Mafioso", ciudadano.verCartaDe(mafioso).getRol());

    }

    @Test
    public void test08JugadorMuertoNopuedeSeguirInteractuando(){
        //Arrange
        Jugador medico = new Jugador("Medico");
        Jugador mafioso = new Jugador("Mafia");
        Jugador ciudadano = new Jugador("Ciudadano");
        RolFactory rolFactory = new RolFactory();

        medico.asignarCarta(rolFactory.crearCartaMedico());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());

        //Act
        mafioso.eliminar();

        //Assert
        assertThrows(ObjetivoInvalidoException.class, () -> mafioso.usarHabilidad(ciudadano));
    }
}
