package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.Habilidades.HabilidadNocturna;
import edu.fiuba.algo3.modelo.Jugador;

public abstract class CartaRol {

    private HabilidadNocturna habilidadNocturna;

    public CartaRol(HabilidadNocturna habilidad)
    {
        this.habilidadNocturna = habilidad;
    }

    public void ejecutarAccionNocturna(Jugador objetivo)
    {
        this.habilidadNocturna.ejecutar(objetivo);
    }

    public abstract boolean esMafia();

    public abstract String obtenerNombre();
}