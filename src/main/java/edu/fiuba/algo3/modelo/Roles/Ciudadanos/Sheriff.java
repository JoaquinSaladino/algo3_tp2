package edu.fiuba.algo3.modelo.Roles.Ciudadanos;

import edu.fiuba.algo3.modelo.Habilidades.Nula;

public class Sheriff extends Ciudadano {

    private Nula habilidad;

    @Override
    public String obtenerNombre()
    {
        return "Sheriff";
    }

}
