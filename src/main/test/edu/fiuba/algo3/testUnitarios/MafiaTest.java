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
        //Arrange
        Jugador ciudadano = new Jugador("J1");
        Jugador mafioso = new Jugador("J2");
        RegistroNocturno registroActual = new RegistroNocturno();
        RolFactory rolFactory = new RolFactory();

        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());

        Nocturna fase = new Nocturna();
        List<Jugador> jugadores = List.of(mafioso, ciudadano);

        //Act
        fase.iniciar(jugadores);
        fase.seleccionarObjetivo(ciudadano);
        fase.ejecutar(jugadores, registroActual);
        //Assert
        assertFalse(ciudadano.estaVivo());
    }

    @Test
    public void test02MafiosoNoPuedeEliminarACiudadanoMuerto()
    {
        //Arrange
        Jugador ciudadano = new Jugador("J1");
        Jugador mafioso = new Jugador("J2");
        RolFactory rolFactory = new RolFactory();

        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());
        //Act
        Nocturna fase1 = new Nocturna();
        RegistroNocturno registro1 = new RegistroNocturno();
        List<Jugador> jugadores = List.of(mafioso, ciudadano);

        //Act
        fase1.iniciar(jugadores);
        fase1.seleccionarObjetivo(ciudadano);
        fase1.ejecutar(jugadores, registro1);
        assertFalse(ciudadano.estaVivo());

        Nocturna fase2 = new Nocturna();
        RegistroNocturno registro2 = new RegistroNocturno();

        fase2.iniciar(jugadores);
        assertFalse(fase2.seleccionarObjetivo(ciudadano));
    }

    @Test
    public void test03MafiosoNoPuedeEliminarAMafioso()
    {
        //Arrange
        Jugador mafioso1 = new Jugador("J1");
        Jugador mafioso2 = new Jugador("J2");
        RolFactory rolFactory = new RolFactory();

        mafioso1.asignarCarta(rolFactory.crearCartaMafioso());
        mafioso2.asignarCarta(rolFactory.crearCartaMafioso());
        //Act & Assert

        Nocturna fase = new Nocturna();
        RegistroNocturno registroActual = new RegistroNocturno();
        List<Jugador> jugadores = List.of(mafioso1, mafioso2);

        //Act
        fase.iniciar(jugadores);
        fase.seleccionarObjetivo(mafioso2);
        fase.ejecutar(jugadores, registroActual);
        //Assert
        assertTrue(mafioso2.estaVivo());
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

        Nocturna fase = new Nocturna();
        RegistroNocturno registroActual = new RegistroNocturno();
        List<Jugador> jugadores = List.of(medico, mafioso, ciudadano);

        //Act
        fase.iniciar(jugadores);
        fase.seleccionarObjetivo(ciudadano);
        fase.avanzarJugador();
        fase.seleccionarObjetivo(ciudadano);
        fase.ejecutar(jugadores, registroActual);
        //Assert
        assertTrue(ciudadano.estaVivo());
    }
}
