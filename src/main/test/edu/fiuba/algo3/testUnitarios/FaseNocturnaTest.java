package edu.fiuba.algo3.testUnitarios;


import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Fases.Nocturna;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;
import org.junit.Test;

import java.util.List;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaseNocturnaTest {
    @Test
    public void test01MafiaEligeJugadorNoProtegidoYEsteSeEliminaAlFinalizarLaFaseNocturna() {
        // Arrange
        Jugador mafiosoMock = new Jugador("Mafioso");
        Jugador victimaMock = new Jugador("Victima");
        mafiosoMock.asignarCarta(new Mafioso());
        victimaMock.asignarCarta(new Ciudadano());
        AccionNocturna aEliminar ;
        // Act
        aEliminar =  mafiosoMock.usarHabilidad(victimaMock);
        //Assert
        assertTrue(victimaMock.estaVivo());
        aEliminar.resolver();
        assertFalse(victimaMock.estaVivo());

    }
}
