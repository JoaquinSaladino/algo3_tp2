package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Configuracion.*;

import edu.fiuba.algo3.modelo.Roles.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EstrategiasBalanceoTest {

    @Test
    public void test01PartidaCon5JugadoresCreaUnMazoCon1Mafioso1DetectiveY3Ciudadanos()
    {
        // Arrange
        EstrategiaBalanceo estrategia = new BalanceoJuegoChico();
        List<CartaRol> cartasRepartidas = new ArrayList<>();

        // Act
        Mazo mazo = estrategia.crearMazo(5);

        while (!mazo.estaVacio())
        {
            cartasRepartidas.add(mazo.repartir());
        }

        // Assert
        assertEquals(5, cartasRepartidas.size(), "Debe haber 5 cartas en total");
        assertEquals(1, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Mafioso")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Detective")).count());
        assertEquals(3, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Ciudadano")).count());
    }

    @Test
    public void test02PartidaCon6JugadoresCreaUnMazoCon2Mafiosos1MedicoY3Ciudadanos()
    {
        // Arrange
        EstrategiaBalanceo estrategia = new BalanceoJuegoChico();
        List<CartaRol> cartasRepartidas = new ArrayList<>();

        // Act
        Mazo mazo = estrategia.crearMazo(6);

        while (!mazo.estaVacio())
        {
            cartasRepartidas.add(mazo.repartir());
        }

        // Assert
        assertEquals(6, cartasRepartidas.size(), "Debe haber 6 cartas en total");
        assertEquals(2, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Mafioso")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Medico")).count());
        assertEquals(3, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Ciudadano")).count());
    }

    @Test
    public void test03PartidaCon7JugadoresCreaUnMazoCon2Mafiosos1Detective1MedicoY3Ciudadanos()
    {
        // Arrange
        EstrategiaBalanceo estrategia = new BalanceoJuegoMediano();
        List<CartaRol> cartasRepartidas = new ArrayList<>();

        // Act
        Mazo mazo = estrategia.crearMazo(7);

        while (!mazo.estaVacio())
        {
            cartasRepartidas.add(mazo.repartir());
        }

        // Assert
        assertEquals(7, cartasRepartidas.size(), "Debe haber 7 cartas en total");
        assertEquals(2, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Mafioso")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Detective")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Medico")).count());
        assertEquals(3, cartasRepartidas.stream().filter(c -> Objects.equals(c.getRol(), "Ciudadano")).count());
    }

    @Test
    public void test04PartidaCon8JugadoresCreaUnMazoCon2Mafiosos1Detective1MedicoY4Ciudadanos()
    {
        // Arrange
        EstrategiaBalanceo estrategia = new BalanceoJuegoMediano();
        List<CartaRol> cartasRepartidas = new ArrayList<>();

        // Act
        Mazo mazo = estrategia.crearMazo(8);

        while (!mazo.estaVacio())
        {
            cartasRepartidas.add(mazo.repartir());
        }

        // Assert
        assertEquals(8, cartasRepartidas.size(), "Debe haber 8 cartas en total");
        assertEquals(2, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Mafioso")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Detective")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Medico")).count());
        assertEquals(4, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Ciudadano")).count());
    }

    @Test
    public void test05PartidaCon9JugadoresCreaUnMazoCon3Mafiosos1Detective1MedicoY4Ciudadanos()
    {
        // Arrange
        EstrategiaBalanceo estrategia = new BalanceoJuegoMediano();
        List<CartaRol> cartasRepartidas = new ArrayList<>();

        // Act
        Mazo mazo = estrategia.crearMazo(9);

        while (!mazo.estaVacio())
        {
            cartasRepartidas.add(mazo.repartir());
        }

        // Assert
        assertEquals(9, cartasRepartidas.size(), "Debe haber 9 cartas en total");
        assertEquals(3, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Mafioso")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Detective")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Medico")).count());
        assertEquals(4, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Ciudadano")).count());
    }

    @Test
    public void test06PartidaCon10JugadoresCreaUnMazoCon2Mafiosos1Padrino1Detective1Medico1SheriffY4Ciudadanos()
    {
        // Arrange
        EstrategiaBalanceo estrategia = new BalanceoJuegoGrande();
        List<CartaRol> cartasRepartidas = new ArrayList<>();

        // Act
        Mazo mazo = estrategia.crearMazo(10);

        while (!mazo.estaVacio())
        {
            cartasRepartidas.add(mazo.repartir());
        }

        // Assert
        assertEquals(10, cartasRepartidas.size(), "Debe haber 10 cartas en total");
        assertEquals(2, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Mafioso")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Padrino")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Detective")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Medico")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Sheriff")).count());
        assertEquals(4, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Ciudadano")).count());
    }

    @Test
    public void test07PartidaCon11JugadoresCreaUnMazoCon2Mafiosos1Padrino1Detective1Medico1SheriffY5Ciudadanos()
    {
        // Arrange
        EstrategiaBalanceo estrategia = new BalanceoJuegoGrande();
        List<CartaRol> cartasRepartidas = new ArrayList<>();

        // Act
        Mazo mazo = estrategia.crearMazo(11);

        while (!mazo.estaVacio())
        {
            cartasRepartidas.add(mazo.repartir());
        }

        // Assert
        assertEquals(11, cartasRepartidas.size(), "Debe haber 11 cartas en total");
        assertEquals(2, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Mafioso")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Padrino")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Detective")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Medico")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Sheriff")).count());
        assertEquals(5, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Ciudadano")).count());
    }

    @Test
    public void test08PartidaCon12JugadoresCreaUnMazoCon2Mafiosos1Padrino1Detective1Medico1SheriffY6Ciudadanos()
    {
        // Arrange
        EstrategiaBalanceo estrategia = new BalanceoJuegoGrande();
        List<CartaRol> cartasRepartidas = new ArrayList<>();

        // Act
        Mazo mazo = estrategia.crearMazo(12);

        while (!mazo.estaVacio())
        {
            cartasRepartidas.add(mazo.repartir());
        }

        // Assert
        assertEquals(12, cartasRepartidas.size(), "Debe haber 12 cartas en total");
        assertEquals(2, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Mafioso")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Padrino")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Detective")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Medico")).count());
        assertEquals(1, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Sheriff")).count());
        assertEquals(6, cartasRepartidas.stream().filter(c ->  Objects.equals(c.getRol(), "Ciudadano")).count());
    }

    @Test
    public void test09PatidaGrandeConMenosDe10Jugadores() {
        //Arrange
        EstrategiaBalanceo estrategiaBalanceo = new BalanceoJuegoGrande();

        //Act Assert
        assertNull(estrategiaBalanceo.crearMazo(5));
    }
}
