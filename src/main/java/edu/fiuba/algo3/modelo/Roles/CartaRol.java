package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Habilidades.HabilidadNocturna;
import edu.fiuba.algo3.modelo.Jugador;

public abstract class CartaRol {

    private HabilidadNocturna habilidadNocturna;
    private boolean esMafia;

    public CartaRol(HabilidadNocturna habilidad, boolean esMafia)
    {
        this.habilidadNocturna = habilidad;
        this.esMafia = esMafia;
    }

    public AccionNocturna generarAccionNocturna(Jugador autor , Jugador objetivo)
    {
        return this.habilidadNocturna.ejecutar(autor,objetivo);
    }

    public boolean esMafia() {
        return esMafia;
    };

    public String investigar(){
        return "Ciudadano";
    }

    public abstract String obtenerRol();

}