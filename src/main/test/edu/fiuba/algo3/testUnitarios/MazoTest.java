package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Configuracion.BalanceoJuegoChico;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Roles.CartaRol;
import edu.fiuba.algo3.modelo.Roles.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Mafioso;
import edu.fiuba.algo3.modelo.Roles.Medico;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MazoTest {

    @Test
    public void test01LasCartasSeMezclanDeFormaAleatoria ()
    {
        // Arrange
        List<CartaRol> cartas = new ArrayList<>();

        cartas.add(new Ciudadano());
        cartas.add(new Ciudadano());
        cartas.add(new Medico());
        cartas.add(new Mafioso());
        cartas.add(new Ciudadano());

        List<CartaRol> copia = new ArrayList<>(cartas);
        Mazo mazo = new Mazo(copia);

        // Act
        mazo.mezclar();

        List<CartaRol> cartasMezcladas = new ArrayList<>();

        while (!mazo.estaVacio())
        {
            cartasMezcladas.add(mazo.repartir());
        }

        // Assert
        assertEquals(5, cartasMezcladas.size(), "No se deben perder cartas al mezclar");
        assertTrue(cartasMezcladas.containsAll(cartas), "Deben estar las mismas cartas");
        assertNotEquals(cartas, cartasMezcladas, "El orden de las cartas debería haber cambiado");

    }
}
