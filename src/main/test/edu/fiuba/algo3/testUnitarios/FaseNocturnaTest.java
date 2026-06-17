package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Fases.Nocturna;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Detective;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Medico;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Padrino;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FaseNocturnaTest {

    @Test
    public void test01MafiaEligeJugadorNoProtegidoYEsteSeEliminaAlFinalizarLaFaseNocturna() {
        // Arrange
        Jugador mafioso = new Jugador("Mafioso");
        Jugador victima = new Jugador("Victima");
        mafioso.asignarCarta(new Mafioso());
        victima.asignarCarta(new Ciudadano());

        Nocturna fase = new Nocturna();
        RegistroNocturno registro = new RegistroNocturno();

        Jugador mafiosoSpy = Mockito.spy(mafioso);
        Mockito.when(mafiosoSpy.obtenerObjetivoElegido()).thenReturn(victima);
        List<Jugador> jugadores = List.of(mafiosoSpy, victima);

        // Act
        assertTrue(victima.estaVivo());
        fase.ejecutar(jugadores, registro);

        // Assert
        assertFalse(victima.estaVivo());
    }
    @Test
    public void test02MafiaVotacionConEmpateSinPadrinoNoEliminaANadie() {
        // Arrange
        Jugador mafioso1 = new Jugador("Mafioso1");
        Jugador mafioso2 = new Jugador("Mafioso2");
        Jugador ciudadano1 = new Jugador("Ciudadano1");
        Jugador ciudadano2 = new Jugador("Ciudadano2");

        mafioso1.asignarCarta(new Mafioso());
        mafioso2.asignarCarta(new Mafioso());
        ciudadano1.asignarCarta(new Ciudadano());
        ciudadano2.asignarCarta(new Ciudadano());

        mafioso1.registrarCompaneros(List.of(mafioso2));
        mafioso2.registrarCompaneros(List.of(mafioso1));

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        // Act: empate sin resolver
        Jugador mafioso1Spy = Mockito.spy(mafioso1);
        Jugador mafioso2Spy = Mockito.spy(mafioso2);
        Mockito.when(mafioso1Spy.obtenerObjetivoElegido()).thenReturn(ciudadano1);
        Mockito.when(mafioso2Spy.obtenerObjetivoElegido()).thenReturn(ciudadano2);
        List<Jugador> jugadores = List.of(mafioso1Spy, mafioso2Spy, ciudadano1, ciudadano2);
        fase.ejecutar(jugadores, registro);

        // Assert: ambos ciudadanos deben estar vivos
        assertTrue(ciudadano1.estaVivo());
        assertTrue(ciudadano2.estaVivo());
    }

    @Test
    public void test03PadrinoRompeEmpateDeMafiaVotoContraDosObjetivos() {
        // Arrange
        Jugador mafioso = new Jugador("Mafioso");
        Jugador padrino = new Jugador("Padrino");
        Jugador ciudadano1 = new Jugador("Ciudadano1");
        Jugador ciudadano2 = new Jugador("Ciudadano2");

        mafioso.asignarCarta(new Mafioso());
        padrino.asignarCarta(new Padrino());
        ciudadano1.asignarCarta(new Ciudadano());
        ciudadano2.asignarCarta(new Ciudadano());

        mafioso.registrarCompaneros(List.of(padrino));
        padrino.registrarCompaneros(List.of(mafioso));

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        // Act
        Jugador mafiosoSpy = Mockito.spy(mafioso);
        Jugador padrinoSpy = Mockito.spy(padrino);
        Mockito.when(mafiosoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano1);
        Mockito.when(padrinoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano2);
        List<Jugador> jugadores = List.of(mafiosoSpy, padrinoSpy, ciudadano1, ciudadano2);

        fase.ejecutar(jugadores, registro);

        // Assert
        assertTrue(ciudadano1.estaVivo());
        assertFalse(ciudadano2.estaVivo());
    }

    @Test
    public void test04MultiplesMafiosisVotanPorObjetivosDiferentesLuegoPadrino() {
        // Arrange
        Jugador mafioso1 = new Jugador("Mafioso1");
        Jugador mafioso2 = new Jugador("Mafioso2");
        Jugador padrino = new Jugador("Padrino");
        Jugador ciudadano1 = new Jugador("Ciudadano1");
        Jugador ciudadano2 = new Jugador("Ciudadano2");
        Jugador ciudadano3 = new Jugador("Ciudadano3");

        mafioso1.asignarCarta(new Mafioso());
        mafioso2.asignarCarta(new Mafioso());
        padrino.asignarCarta(new Padrino());
        ciudadano1.asignarCarta(new Ciudadano());
        ciudadano2.asignarCarta(new Ciudadano());
        ciudadano3.asignarCarta(new Ciudadano());

        List<Jugador> mafiosos = List.of(mafioso1, mafioso2, padrino);
        for (Jugador mafioso : mafiosos) {
            mafioso.registrarCompaneros(mafiosos.stream().filter(jugador -> jugador != mafioso).collect(Collectors.toList()));
        }

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();
        List<Jugador> jugadores = List.of(mafioso1, mafioso2, padrino, ciudadano1, ciudadano2, ciudadano3);

        // Act:
        Jugador mafioso1Spy = Mockito.spy(mafioso1);
        Jugador mafioso2Spy = Mockito.spy(mafioso2);
        Jugador padrinoSpy = Mockito.spy(padrino);
        Mockito.when(mafioso1Spy.obtenerObjetivoElegido()).thenReturn(ciudadano1);
        Mockito.when(mafioso2Spy.obtenerObjetivoElegido()).thenReturn(ciudadano1);
        Mockito.when(padrinoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano2);
        List<Jugador> jugadoresSpy = List.of(mafioso1Spy, mafioso2Spy, padrinoSpy, ciudadano1, ciudadano2, ciudadano3);
        fase.ejecutar(jugadoresSpy, registro);

        // Assert
        assertFalse(ciudadano1.estaVivo());
        assertTrue(ciudadano2.estaVivo());
    }

    @Test
    public void test05VariosMafiosisVotanPorMismaVictima() {
        // Arrange
        Jugador mafioso1 = new Jugador("Mafioso1");
        Jugador mafioso2 = new Jugador("Mafioso2");
        Jugador ciudadano = new Jugador("Ciudadano");

        mafioso1.asignarCarta(new Mafioso());
        mafioso2.asignarCarta(new Mafioso());
        ciudadano.asignarCarta(new Ciudadano());

        mafioso1.registrarCompaneros(List.of(mafioso2));
        mafioso2.registrarCompaneros(List.of(mafioso1));

        Nocturna fase = new Nocturna();
        RegistroNocturno registro = new RegistroNocturno();
        Jugador mafioso1Spy = Mockito.spy(mafioso1);
        Mockito.when(mafioso1Spy.obtenerObjetivoElegido()).thenReturn(ciudadano);
        Jugador mafioso2Spy = Mockito.spy(mafioso2);
        Mockito.when(mafioso2Spy.obtenerObjetivoElegido()).thenReturn(ciudadano);

        List<Jugador> jugadores = List.of(mafioso1Spy, mafioso2Spy, ciudadano);

        // Act
        fase.ejecutar(jugadores, registro);

        // Assert
        assertFalse(ciudadano.estaVivo());
    }

    @Test
    public void test06MedicoProtegeCiudadanoYMafiaNoLoPuedeEliminar() {
        // Arrange
        Jugador mafioso = new Jugador("Mafioso");
        Jugador medico = new Jugador("Medico");
        Jugador ciudadano = new Jugador("Ciudadano");

        mafioso.asignarCarta(new Mafioso());
        medico.asignarCarta(new Medico());
        ciudadano.asignarCarta(new Ciudadano());

        Nocturna fase = new Nocturna();
        RegistroNocturno registro = new RegistroNocturno();

        Jugador mafiosoSpy = Mockito.spy(mafioso);
        Mockito.when(mafiosoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);
        Jugador medicoSpy = Mockito.spy(medico);
        Mockito.when(medicoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);

        List<Jugador> jugadores = List.of(mafiosoSpy, medicoSpy, ciudadano);

        // Act
        fase.ejecutar(jugadores, registro);

        // Assert
        assertTrue(ciudadano.estaVivo());
    }

    @Test
    public void test07MedicoNoPuedeProtegerAlMismoObjetivoDosFasesConsecutivas() {
        // Arrange
        Jugador medico = new Jugador("Medico");
        Jugador ciudadano = new Jugador("Ciudadano");
        Jugador mafioso = new Jugador("Mafioso");

        medico.asignarCarta(new Medico());
        ciudadano.asignarCarta(new Ciudadano());
        mafioso.asignarCarta(new Mafioso());

        Nocturna fase1 = new Nocturna();
        RegistroNocturno registro1 = new RegistroNocturno();

        Jugador mafiosoSpy = Mockito.spy(mafioso);
        Mockito.when(mafiosoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);
        Jugador medicoSpy = Mockito.spy(medico);
        Mockito.when(medicoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);
        List<Jugador> jugadoresSpy = List.of(medicoSpy, ciudadano, mafiosoSpy);
        fase1.ejecutar(jugadoresSpy, registro1);

        assertTrue(ciudadano.estaVivo());

        Nocturna fase2 = new Nocturna();
        RegistroNocturno registro2 = new RegistroNocturno();
        fase2.ejecutar(jugadoresSpy, registro2);

        // Assert
        assertFalse(ciudadano.estaVivo());
    }

    @Test
    public void test08MedicoMuertoNoPuedeUsarHabilidades() {
        // Arrange
        Jugador medico = new Jugador("Medico");
        Jugador ciudadano = new Jugador("Ciudadano");
        Jugador mafioso = new Jugador("Mafioso");

        medico.asignarCarta(new Medico());
        ciudadano.asignarCarta(new Ciudadano());
        mafioso.asignarCarta(new Mafioso());

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        Jugador mafiosoSpy = Mockito.spy(mafioso);
        Mockito.when(mafiosoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);
        Jugador medicoSpy = Mockito.spy(medico);
        Mockito.when(medicoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);
        Mockito.when(medicoSpy.estaVivo()).thenReturn(false);
        List<Jugador> jugadoresSpy = List.of(medicoSpy, ciudadano, mafiosoSpy);
        // Act
        fase.ejecutar(jugadoresSpy, registro);
        // Assert
        assertFalse(ciudadano.estaVivo());
    }

    @Test
    public void test09DetectiveInvestigaEnFaseNocturnaYObtieneResultadoEnRegistro() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador mafioso = new Jugador("Mafioso");
        Jugador ciudadano = new Jugador("Ciudadano");

        detective.asignarCarta(new Detective());
        mafioso.asignarCarta(new Mafioso());
        ciudadano.asignarCarta(new Ciudadano());

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        // Act
        Jugador detectiveSpy = Mockito.spy(detective);
        Mockito.when(detectiveSpy.obtenerObjetivoElegido()).thenReturn(mafioso);
        List<Jugador> jugadores = List.of(detectiveSpy, mafioso, ciudadano);
        fase.ejecutar(jugadores, registro);

        // Assert
        assertEquals("Mafia", registro.obtenerResultadoInvestigacion(detectiveSpy));
    }

    @Test
    public void test10DetectiveInvestigaPadrinoYObtieneResultadoComoCiudadano() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador padrino = new Jugador("Padrino");

        detective.asignarCarta(new Detective());
        padrino.asignarCarta(new Padrino());

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        // Act
        Jugador detectiveSpy = Mockito.spy(detective);
        Mockito.when(detectiveSpy.obtenerObjetivoElegido()).thenReturn(padrino);
        List<Jugador> jugadores = List.of(detectiveSpy, padrino);
        fase.ejecutar(jugadores, registro);

        // Assert
        assertEquals("Ciudadano", registro.obtenerResultadoInvestigacion(detectiveSpy));
    }





    @Test
    public void test11DetectiveNoPuedeInvestigarAlMismoObjetivoDosFasesConsecutivas() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador sospechoso = new Jugador("Sospechoso");

        detective.asignarCarta(new Detective());
        sospechoso.asignarCarta(new Ciudadano());

        Nocturna fase1 = new Nocturna();
        RegistroNocturno registro1 = new RegistroNocturno();
        List<Jugador> jugadores = List.of(detective, sospechoso);

        // Act: Primera noche, detective investiga
        Jugador detectiveSpy = Mockito.spy(detective);
        Mockito.when(detectiveSpy.obtenerObjetivoElegido()).thenReturn(sospechoso);
        List<Jugador> jugadoresSpy = List.of(detectiveSpy, sospechoso);
        fase1.ejecutar(jugadoresSpy, registro1);

        // Assert primera noche
        assertNotNull(registro1.obtenerResultadoInvestigacion(detectiveSpy));

        // Segunda noche: detective intenta investigar de nuevo, la fase lanzará la excepción
        Nocturna fase2 = new Nocturna();
        RegistroNocturno registro2 = new RegistroNocturno();
        Mockito.when(detectiveSpy.obtenerObjetivoElegido()).thenReturn(sospechoso);
        assertThrows(
                edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException.class,
                () -> fase2.ejecutar(jugadoresSpy, registro2)
        );
    }

}
