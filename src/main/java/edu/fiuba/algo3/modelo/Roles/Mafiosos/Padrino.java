package edu.fiuba.algo3.modelo.Roles.Mafiosos;


public class Padrino extends Mafioso {


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
