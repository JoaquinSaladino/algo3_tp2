package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.Habilidades.Investigar;

public class Detective extends Ciudadano {

    private Investigar habilidad;

    @Override
    public String obtenerNombre()
    {
        return "Detective";
    }
}
