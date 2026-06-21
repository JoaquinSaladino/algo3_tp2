package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Excepciones.MazoVacioException;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

import edu.fiuba.algo3.modelo.Roles.RolFactory;
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
        RolFactory rolFactory = new RolFactory();

        cartas.add(rolFactory.crearCartaCiudadano());
        cartas.add(rolFactory.crearCartaCiudadano());
        cartas.add(rolFactory.crearCartaMedico());
        cartas.add(rolFactory.crearCartaMafioso());
        cartas.add(rolFactory.crearCartaCiudadano());

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

    @Test
    public void test02RepartirEnMazoVacioLanzaExcepcion() {
        Mazo mazo = new Mazo(new ArrayList<>());

        assertThrows(MazoVacioException.class, () -> mazo.repartir());
    }
}
