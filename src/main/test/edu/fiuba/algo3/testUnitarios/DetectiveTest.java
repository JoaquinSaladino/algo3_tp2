package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Detective;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Padrino;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetectiveTest {
    @Test
    public void test01DetectiveInvestigaAMafiosoYObtieneResultadoMafia() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador mafioso = new Jugador("Mafioso");
        detective.asignarCarta(new Detective());
        mafioso.asignarCarta(new Mafioso());
        RegistroNocturno registro = new RegistroNocturno();
        //Act
        detective.usarHabilidad(mafioso).resolver(registro);
        //Assert
        assertEquals("Mafia", registro.obtenerResultadoInvestigacion(detective));
    }

    @Test
    public void test02DetectiveInvestigaCiudadanoYObtieneResultadoCiudadano() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador ciudadano = new Jugador("Ciudadano");

        detective.asignarCarta(new Detective());
        ciudadano.asignarCarta(new Ciudadano());

        RegistroNocturno registro = new RegistroNocturno();

        // Act
        detective.usarHabilidad(ciudadano).resolver(registro);

        // Assert
        assertEquals(
                "Ciudadano",
                registro.obtenerResultadoInvestigacion(detective)
        );
    }

    @Test
    public void test03DetectiveInvestigaPadrinoYObtieneResultadoCiudadano() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador padrino = new Jugador("Padrino");
        detective.asignarCarta(new Detective());
        padrino.asignarCarta(new Padrino());
        RegistroNocturno registro = new RegistroNocturno();
        // Act
        detective.usarHabilidad(padrino).resolver(registro);
        // Assert
        assertEquals("Ciudadano", registro.obtenerResultadoInvestigacion(detective));
    }
}
