package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Habilidades.HabilidadNocturna;
import edu.fiuba.algo3.modelo.Jugador;

public abstract class CartaRol {

    private HabilidadNocturna habilidadNocturna;

    public CartaRol(HabilidadNocturna habilidad)
    {
        this.habilidadNocturna = habilidad;
    }

    public AccionNocturna ejecutarAccionNocturna(Jugador autor ,Jugador objetivo)
    {
        return this.habilidadNocturna.ejecutar(autor,objetivo);
    }

    public abstract boolean esMafia();

    public abstract String obtenerNombre();

}