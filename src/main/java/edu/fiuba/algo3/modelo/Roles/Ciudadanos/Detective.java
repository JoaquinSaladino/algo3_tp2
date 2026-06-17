package edu.fiuba.algo3.modelo.Roles.Ciudadanos;

import edu.fiuba.algo3.modelo.Habilidades.Investigar;

public class Detective extends Ciudadano {

    public Detective() {
        super(new Investigar());
    }
    @Override
    public String obtenerRol()
    {
        return "Detective";
    }
}
