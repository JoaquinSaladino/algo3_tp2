package edu.fiuba.algo3.modelo.Roles.Ciudadanos;

import edu.fiuba.algo3.modelo.Habilidades.Nula;
import edu.fiuba.algo3.modelo.Habilidades.Proteger;

public class Medico extends Ciudadano {

    public Medico()
    {
        super(new Proteger());
    }

    @Override
    public String obtenerNombre()
    {
        return "Medico";
    }


}
