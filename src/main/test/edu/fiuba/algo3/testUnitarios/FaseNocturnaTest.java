package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Fases.Nocturna;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;
import edu.fiuba.algo3.modelo.Roles.RolFactory;
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
        RolFactory rolFactory = new RolFactory();
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());
        victima.asignarCarta(rolFactory.crearCartaCiudadano());

        Nocturna fase = new Nocturna();
        RegistroNocturno registro = new RegistroNocturno();

        Jugador mafiosoSpy = Mockito.spy(mafioso);
        List<Jugador> jugadores = List.of(mafiosoSpy, victima);

        // Act
        fase.iniciar(jugadores);
        fase.seleccionarObjetivo(victima);
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
        RolFactory rolFactory = new RolFactory();

        mafioso1.asignarCarta(rolFactory.crearCartaMafioso());
        mafioso2.asignarCarta(rolFactory.crearCartaMafioso());
        ciudadano1.asignarCarta(rolFactory.crearCartaCiudadano());
        ciudadano2.asignarCarta(rolFactory.crearCartaCiudadano());

        mafioso1.registrarCompaneros(List.of(mafioso2));
        mafioso2.registrarCompaneros(List.of(mafioso1));

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        // Act: empate sin resolver
        Jugador mafioso1Spy = Mockito.spy(mafioso1);
        Jugador mafioso2Spy = Mockito.spy(mafioso2);
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
        RolFactory rolFactory = new RolFactory();

        mafioso.asignarCarta(rolFactory.crearCartaMafioso());
        padrino.asignarCarta(rolFactory.crearCartaPadrino());
        ciudadano1.asignarCarta(rolFactory.crearCartaCiudadano());
        ciudadano2.asignarCarta(rolFactory.crearCartaCiudadano());

        mafioso.registrarCompaneros(List.of(padrino));
        padrino.registrarCompaneros(List.of(mafioso));

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        // Act
        Jugador mafiosoSpy = Mockito.spy(mafioso);
        Jugador padrinoSpy = Mockito.spy(padrino);
        List<Jugador> jugadores = List.of(mafiosoSpy, padrinoSpy, ciudadano1, ciudadano2);

        fase.iniciar(jugadores);
        fase.seleccionarObjetivo(ciudadano1);
        fase.avanzarJugador();
        fase.seleccionarObjetivo(ciudadano2);
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
        RolFactory rolFactory = new RolFactory();

        mafioso1.asignarCarta(rolFactory.crearCartaMafioso());
        mafioso2.asignarCarta(rolFactory.crearCartaMafioso());
        padrino.asignarCarta(rolFactory.crearCartaPadrino());
        ciudadano1.asignarCarta(rolFactory.crearCartaCiudadano());
        ciudadano2.asignarCarta(rolFactory.crearCartaCiudadano());
        ciudadano3.asignarCarta(rolFactory.crearCartaCiudadano());

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
        List<Jugador> jugadoresSpy = List.of(mafioso1Spy, mafioso2Spy, padrinoSpy, ciudadano1, ciudadano2, ciudadano3);

        fase.iniciar(jugadoresSpy);
        fase.seleccionarObjetivo(ciudadano1);
        fase.avanzarJugador();
        fase.seleccionarObjetivo(ciudadano1);
        fase.avanzarJugador();
        fase.seleccionarObjetivo(ciudadano2);
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
        RolFactory rolFactory = new RolFactory();

        mafioso1.asignarCarta(rolFactory.crearCartaMafioso());
        mafioso2.asignarCarta(rolFactory.crearCartaMafioso());
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());

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
        fase.iniciar(jugadores);
        fase.seleccionarObjetivo(ciudadano);
        fase.avanzarJugador();
        fase.seleccionarObjetivo(ciudadano);
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
        RolFactory rolFactory = new RolFactory();

        mafioso.asignarCarta(rolFactory.crearCartaMafioso());
        medico.asignarCarta(rolFactory.crearCartaMedico());
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());

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
        RolFactory rolFactory = new RolFactory();

        medico.asignarCarta(rolFactory.crearCartaMedico());
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());
        //Act & Assert
        Nocturna fase1 = new Nocturna();
        RegistroNocturno registro1 = new RegistroNocturno();

        List<Jugador> jugadores = List.of(medico, ciudadano, mafioso);

        fase1.iniciar(jugadores);
        assertTrue(fase1.seleccionarObjetivo(ciudadano));
        fase1.ejecutar(jugadores, registro1);
        assertTrue(ciudadano.estaVivo());

        Nocturna fase2 = new Nocturna();
        fase2.iniciar(jugadores);
        assertFalse(fase2.seleccionarObjetivo(ciudadano));
    }

    @Test
    public void test08MedicoMuertoNoPuedeUsarHabilidades() {
        // Arrange
        Jugador medico = new Jugador("Medico");
        Jugador ciudadano = new Jugador("Ciudadano");
        Jugador mafioso = new Jugador("Mafioso");
        RolFactory rolFactory = new RolFactory();

        medico.asignarCarta(rolFactory.crearCartaMedico());
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        Jugador mafiosoSpy = Mockito.spy(mafioso);
        Mockito.when(mafiosoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);
        Jugador medicoSpy = Mockito.spy(medico);
        Mockito.when(medicoSpy.obtenerObjetivoElegido()).thenReturn(ciudadano);
        Mockito.when(medicoSpy.estaVivo()).thenReturn(false);
        List<Jugador> jugadoresSpy = List.of(medicoSpy, ciudadano, mafiosoSpy);
        // Act
        fase.iniciar(jugadoresSpy);

        fase.avanzarJugador();
        fase.seleccionarObjetivo(ciudadano);

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
        RolFactory rolFactory = new RolFactory();

        detective.asignarCarta(rolFactory.crearCartaDetective());
        mafioso.asignarCarta(rolFactory.crearCartaMafioso());
        ciudadano.asignarCarta(rolFactory.crearCartaCiudadano());

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        // Act
        List<Jugador> jugadores = List.of(detective, mafioso, ciudadano);
        fase.iniciar(jugadores);
        fase.seleccionarObjetivo(mafioso);
        fase.ejecutar(jugadores, registro);
        // Assert
        assertEquals("Mafia", registro.obtenerResultadoInvestigacion(detective));
    }

    @Test
    public void test10DetectiveInvestigaPadrinoYObtieneResultadoComoCiudadano() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador padrino = new Jugador("Padrino");
        RolFactory rolFactory = new RolFactory();

        detective.asignarCarta(rolFactory.crearCartaDetective());
        padrino.asignarCarta(rolFactory.crearCartaPadrino());

        RegistroNocturno registro = new RegistroNocturno();
        Nocturna fase = new Nocturna();

        // Act
        List<Jugador> jugadores = List.of(detective, padrino);
        fase.iniciar(jugadores);
        fase.seleccionarObjetivo(padrino);
        fase.ejecutar(jugadores, registro);

        // Assert
        assertEquals("Ciudadano", registro.obtenerResultadoInvestigacion(detective));
    }





    @Test
    public void test11DetectiveNoPuedeInvestigarAlMismoObjetivoDosFasesConsecutivas() {
        // Arrange
        Jugador detective = new Jugador("Detective");
        Jugador sospechoso = new Jugador("Sospechoso");
        RolFactory rolFactory = new RolFactory();

        detective.asignarCarta(rolFactory.crearCartaDetective());
        sospechoso.asignarCarta(rolFactory.crearCartaCiudadano());

        Nocturna fase1 = new Nocturna();
        RegistroNocturno registro1 = new RegistroNocturno();
        List<Jugador> jugadores = List.of(detective, sospechoso);

        // Act: Primera noche, detective investiga

        fase1.iniciar(jugadores);
        assertTrue(fase1.seleccionarObjetivo(sospechoso));
        fase1.ejecutar(jugadores, registro1);

        // Assert
        assertNotNull(registro1.obtenerResultadoInvestigacion(detective));

        // Segunda noche: detective intenta investigar de nuevo, la fase lanzará la excepción
        Nocturna fase2 = new Nocturna();
        RegistroNocturno registro2 = new RegistroNocturno();
        fase2.iniciar(jugadores);
        assertFalse(fase2.seleccionarObjetivo(sospechoso));
    }

}
