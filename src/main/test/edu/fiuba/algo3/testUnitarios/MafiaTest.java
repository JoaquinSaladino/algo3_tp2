package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MafiaTest {

    @Test
    public void test01MafiosoPuedeEliminarACiudadanoVivo()
    {
        Jugador ciudadano = new Jugador("J1");
        Jugador mafioso = new Jugador("J2");

        ciudadano.asignarCarta(new Ciudadano());
        mafioso.asignarCarta(new Mafioso());

        (mafioso.usarHabilidad(ciudadano)).resolver();

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
}
