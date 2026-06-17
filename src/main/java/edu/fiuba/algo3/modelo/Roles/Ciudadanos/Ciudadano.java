package edu.fiuba.algo3.modelo.Roles.Ciudadanos;

import edu.fiuba.algo3.modelo.Habilidades.HabilidadNocturna;
import edu.fiuba.algo3.modelo.Habilidades.Nula;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

public class Ciudadano extends CartaRol {

    public Ciudadano()
    {
        super(new Nula());
    }
    public Ciudadano(HabilidadNocturna habilidad) {
        super(habilidad);
    }
    @Override
    public boolean esMafia()
    {
        return false;
    }

    @Override
    public String obtenerRol()
    {
        return "Ciudadano";
    }

}