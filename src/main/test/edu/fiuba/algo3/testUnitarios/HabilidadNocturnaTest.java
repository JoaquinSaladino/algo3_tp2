package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Habilidades.HabilidadNocturna;
import edu.fiuba.algo3.modelo.Habilidades.Investigar;
import edu.fiuba.algo3.modelo.Habilidades.Proteger;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HabilidadNocturnaTest {
    @Test
    public void test01LaHabilidadProtegerNoPermiteUsarlaDosVecesEnElMismoObjetivo(){
        //Arrenge
        HabilidadNocturna proteger = new Proteger();
        Jugador jugadorAutor = Mockito.mock(Jugador.class);
        Jugador jugadorObjetivo = Mockito.mock(Jugador.class);
        //Act
        proteger.ejecutar(jugadorAutor,jugadorObjetivo);
        //Assert
        assertThrows(
                ObjetivoInvalidoException.class,
                () -> proteger.ejecutar(jugadorAutor,jugadorObjetivo));
    }

    @Test
    public void test02LaHabilidadInvestigarNoPermiteUsarlaDosVecesEnElMismoObjetivo(){
        //Arrenge
        HabilidadNocturna investigar = new Investigar();
        Jugador jugadorAutor = Mockito.mock(Jugador.class);
        Jugador jugadorObjetivo = Mockito.mock(Jugador.class);
        //Act
        investigar.ejecutar(jugadorAutor,jugadorObjetivo);
        //Assert
        assertThrows(
                ObjetivoInvalidoException.class,
                () -> investigar.ejecutar(jugadorAutor, jugadorObjetivo)
        );
    }
}
