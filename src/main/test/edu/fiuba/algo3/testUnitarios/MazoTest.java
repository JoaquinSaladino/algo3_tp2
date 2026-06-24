package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Configuracion.ConfiguracionPartida;
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
        //Arrange
        Mazo mazo = new Mazo(new ArrayList<>());
        //Act & Assert
        assertThrows(MazoVacioException.class, () -> mazo.repartir());
    }

    @Test
    public void test03ConfiguracionConMenosDe5Jugadores(){
        assertThrows(IllegalArgumentException.class, () -> new ConfiguracionPartida(4));
    }

    @Test
    public void test04ConfiguracionConMasDe12Jugadores(){
        assertThrows(IllegalArgumentException.class, () -> new ConfiguracionPartida(13));
    }

    @Test
    public void test05GenerarMazoCon5JugadoresUsaBalanceoChico() {
        ConfiguracionPartida config = new ConfiguracionPartida(5);
        Mazo mazo = config.generarMazo();
        int count = 0;
        while (!mazo.estaVacio()) { mazo.repartir(); count++; }
        assertEquals(5, count);
    }

    @Test
    public void test06GenerarMazoCon8JugadoresUsaBalanceoChico() {
        ConfiguracionPartida config = new ConfiguracionPartida(8);
        Mazo mazo = config.generarMazo();
        int count = 0;
        while (!mazo.estaVacio()) { mazo.repartir(); count++; }
        assertEquals(8, count);
    }

    @Test
    public void test07GenerarMazoCon12JugadoresUsaBalanceoChico() {
        ConfiguracionPartida config = new ConfiguracionPartida(12);
        Mazo mazo = config.generarMazo();
        int count = 0;
        while (!mazo.estaVacio()) { mazo.repartir(); count++; }
        assertEquals(12, count);
    }



}
