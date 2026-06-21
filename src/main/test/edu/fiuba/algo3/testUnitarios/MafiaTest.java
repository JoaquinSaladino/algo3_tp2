package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Fases.Nocturna;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.RolFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MafiaTest {

    @Test
    public void test01MafiosoPuedeEliminarACiudadanoVivo()
    {
        Jugador ciudadano = new Jugador("J1");
        Jugador mafioso = new Jugador("J2");
        RegistroNocturno registroActual = new RegistroNocturno();
        RolFactory rolFactory = new RolFactory();

        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());

//        (mafioso.usarHabilidad(ciudadano)).resolver(registroActual);

        Jugador mafiosoSpy = Mockito.spy(mafioso);
        Mockito.when(mafiosoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);

        Nocturna fase = new Nocturna();
        fase.ejecutar(List.of(mafiosoSpy, ciudadano), registroActual);
        assertFalse(ciudadano.estaVivo());
    }

    @Test
    public void test02MafiosoNoPuedeEliminarACiudadanoMuerto()
    {

        Jugador ciudadano = new Jugador("J1");
        Jugador mafioso = new Jugador("J2");
        RolFactory rolFactory = new RolFactory();

        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());

        ciudadano.eliminar();

        assertThrows(ObjetivoInvalidoException.class, () -> mafioso.usarHabilidad(ciudadano));
    }

    @Test
    public void test03MafiosoNoPuedeEliminarAMafioso()
    {
        Jugador mafioso1 = new Jugador("J1");
        Jugador mafioso2 = new Jugador("J2");
        RolFactory rolFactory = new RolFactory();

        mafioso1.asignarCarta(rolFactory.crearCartaMafioso());
        mafioso2.asignarCarta(rolFactory.crearCartaMafioso());

        assertThrows(ObjetivoInvalidoException.class, () -> mafioso1.usarHabilidad(mafioso2));
    }

    @Test
    public void test04MafiosoNoPuedeEliminarACiudadanoProtegido()
    {
        //Arrange
        RolFactory rolFactory = new RolFactory();

        Jugador mafioso = new Jugador("mafioso");
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());

        Jugador medico = new Jugador("medico");
        medico.asignarCarta(rolFactory.crearCartaMedico());

        Jugador ciudadano = new Jugador("ciudadano");
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());

        RegistroNocturno registroActual = new RegistroNocturno();

        //Act
        medico.usarHabilidad(ciudadano).resolver(registroActual);
        mafioso.usarHabilidad(ciudadano).resolver(registroActual);

        //Assert
        assertTrue(ciudadano.estaVivo());

    }
}
