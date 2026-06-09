package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.Nula;

public class Ciudadano extends CartaRol {

    public Ciudadano()
    {
        super(new Nula());
    }

    @Override
    public boolean esMafia()
    {
        return false;
    }

}