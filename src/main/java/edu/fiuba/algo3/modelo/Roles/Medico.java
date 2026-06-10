package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.Habilidades.Proteger;

public class Medico extends Ciudadano {

    private Proteger habilidad;

    @Override
    public String obtenerNombre()
    {
        return "Medico";
    }
}
