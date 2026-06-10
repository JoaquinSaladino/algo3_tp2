package edu.fiuba.algo3.modelo.Roles.Mafiosos;

import edu.fiuba.algo3.modelo.Habilidades.Eliminar;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

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

    @Override
    public String obtenerNombre()
    {
        return "Mafioso";
    }

}