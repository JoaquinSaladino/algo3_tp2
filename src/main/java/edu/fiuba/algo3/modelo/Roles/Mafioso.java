package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.Eliminar;

public class Mafioso extends CartaRol {

    public Mafioso()
    {
        super(new Eliminar());
    }

    @Override
    public boolean esMafia()
    {
        return true;
    }

}