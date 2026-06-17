package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Medico;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MafiaTest {

    @Test
    public void test01MafiosoPuedeEliminarACiudadanoVivo()
    {
        Jugador ciudadano = new Jugador("J1");
        Jugador mafioso = new Jugador("J2");
        RegistroNocturno registroActual = new RegistroNocturno();

        ciudadano.asignarCarta(new Ciudadano());
        mafioso.asignarCarta(new Mafioso());

        (mafioso.usarHabilidad(ciudadano)).resolver(registroActual);

        assertFalse(ciudadano.estaVivo());
    }

    @Test
    public void test02MafiosoNoPuedeEliminarACiudadanoMuerto()
    {

        Jugador ciudadano = new Jugador("J1");
        Jugador mafioso = new Jugador("J2");

        ciudadano.asignarCarta(new Ciudadano());
        mafioso.asignarCarta(new Mafioso());

        ciudadano.eliminar();

        assertThrows(ObjetivoInvalidoException.class, () -> mafioso.usarHabilidad(ciudadano));
    }

    @Test
    public void test03MafiosoNoPuedeEliminarAMafioso()
    {
        Jugador mafioso1 = new Jugador("J1");
        Jugador mafioso2 = new Jugador("J2");

        mafioso1.asignarCarta(new Mafioso());
        mafioso2.asignarCarta(new Mafioso());

        assertThrows(ObjetivoInvalidoException.class, () -> mafioso1.usarHabilidad(mafioso2));
    }

    @Test
    public void test04MafiosoNoPuedeEliminarACiudadanoProtegido()
    {
        //Arrange
        Jugador mafioso = new Jugador("mafioso");
        mafioso.asignarCarta(new Mafioso());

        Jugador medico = new Jugador("medico");
        medico.asignarCarta(new Medico());

        Jugador ciudadano = new Jugador("ciudadano");
        ciudadano.asignarCarta(new Ciudadano());

        RegistroNocturno registroActual = new RegistroNocturno();

        //Act
        medico.usarHabilidad(ciudadano).resolver(registroActual);
        mafioso.usarHabilidad(ciudadano).resolver(registroActual);

        //Assert
        assertTrue(ciudadano.estaVivo());

    }
}
