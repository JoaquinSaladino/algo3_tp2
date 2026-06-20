package edu.fiuba.algo3.modelo.Roles.Mafiosos;

import edu.fiuba.algo3.modelo.Habilidades.Seleccionar;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

public class Mafioso extends CartaRol {

    public Mafioso()
    {
        super(new Seleccionar(), true);
    }

    @Override
    public String investigar() {
        return this.obtenerRol();
    }

    @Override
    public String obtenerRol()
    {
        return "Mafia";
    }

}