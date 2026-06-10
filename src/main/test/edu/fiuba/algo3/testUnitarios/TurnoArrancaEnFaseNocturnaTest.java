package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Fases.Diurna;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Fases.Nocturna;
import edu.fiuba.algo3.modelo.Turno;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class TurnoArrancaEnFaseNocturnaTest {
    @Test
    public void testTurnoArrancaEnFaseNocturna(){
        Nocturna nocturnaMock = Mockito.mock(Nocturna.class);
        Diurna diurnaMock = Mockito.mock(Diurna.class);
        Jugador jugadorMock = Mockito.mock(Jugador.class);
        List<Jugador> jugadores = List.of(jugadorMock);

        Turno turno = new Turno(nocturnaMock, diurnaMock);
        turno.ejecutarFaseActual(jugadores);

        Mockito.verify(nocturnaMock).ejecutar(jugadores);
        Mockito.verify(diurnaMock, Mockito.never()).ejecutar(jugadores);
    }
}
