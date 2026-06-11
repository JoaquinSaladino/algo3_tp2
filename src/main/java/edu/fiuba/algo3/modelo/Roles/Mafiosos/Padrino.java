package edu.fiuba.algo3.modelo.Roles.Mafiosos;

import edu.fiuba.algo3.modelo.Habilidades.Eliminar;

public class Padrino extends Mafioso {

    @Override
    public boolean esMafia() {
        return false;
    }

    @Override
    public String obtenerNombre()
    {
        return "Padrino";
    }
}
