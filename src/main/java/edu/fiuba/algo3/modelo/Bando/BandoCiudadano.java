package edu.fiuba.algo3.modelo.Bando;

public class BandoCiudadano implements Bando {
    @Override
    public boolean esMafia() {
        return false ;
    }

    @Override
    public String obtenerResultadoInvestigacion() {
        return "Ciudadano";
    }
}
