package edu.fiuba.algo3.modelo.Habilidades;

import edu.fiuba.algo3.modelo.AccionNocturna.ASeleccionar;
import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;

public class Seleccionar  implements HabilidadNocturna {

    @Override
    public AccionNocturna ejecutar(Jugador autor, Jugador objetivo) {
        if (!objetivo.estaVivo()) {
            System.out.println("hola");
            throw new ObjetivoInvalidoException();
        }
        if (objetivo.esMafia()) {
            System.out.println("hola2");
            throw new ObjetivoInvalidoException();
        }
        return new ASeleccionar(autor,objetivo);
    }
}