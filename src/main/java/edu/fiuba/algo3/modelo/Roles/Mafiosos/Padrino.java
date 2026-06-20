package edu.fiuba.algo3.modelo.Roles.Mafiosos;


import edu.fiuba.algo3.modelo.Habilidades.Seleccionar;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

public class Padrino extends CartaRol {

    public Padrino(){
        super(new Seleccionar(), true);
    }
    @Override
    public String investigar() {
        return "Ciudadano";
    }

    @Override
    public String obtenerRol()
    {
        return "Padrino";
    }
}
