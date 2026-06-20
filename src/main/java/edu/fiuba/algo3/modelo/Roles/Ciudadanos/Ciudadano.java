package edu.fiuba.algo3.modelo.Roles.Ciudadanos;

import edu.fiuba.algo3.modelo.Habilidades.HabilidadNocturna;
import edu.fiuba.algo3.modelo.Habilidades.Nula;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

public class Ciudadano extends CartaRol {

    public Ciudadano()
    {
        super(new Nula(), false);
    }
    public Ciudadano(HabilidadNocturna habilidad) {
        super(habilidad, false);
    }

    @Override
    public String obtenerRol()
    {
        return "Ciudadano";
    }

}