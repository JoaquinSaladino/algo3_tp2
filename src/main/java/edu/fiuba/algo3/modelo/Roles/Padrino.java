package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.Eliminar;

public class Padrino extends Mafioso {
    private Eliminar habilidad;

    @Override
    public boolean esMafia() {
        return false;
    }
}
