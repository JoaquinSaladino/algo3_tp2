package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.RolFactory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetectiveTest {
    @Test
    public void test01DetectiveInvestigaAMafiosoYObtieneResultadoMafia() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador mafioso = new Jugador("Mafioso");
        RolFactory rolFactory = new RolFactory();
        detective.asignarCarta(rolFactory.crearCartaDetective());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());
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
        RolFactory rolFactory = new RolFactory();
        detective.asignarCarta(rolFactory.crearCartaDetective());
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());

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
        RolFactory rolFactory = new RolFactory();
        detective.asignarCarta(rolFactory.crearCartaDetective());
        padrino.asignarCarta(rolFactory.crearCartaPadrino());
        RegistroNocturno registro = new RegistroNocturno();
        // Act
        detective.usarHabilidad(padrino).resolver(registro);
        // Assert
        assertEquals("Ciudadano", registro.obtenerResultadoInvestigacion(detective));
    }
}
