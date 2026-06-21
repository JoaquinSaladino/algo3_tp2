package edu.fiuba.algo3.modelo.Bando;

public class BandoMafia implements Bando {
    @Override
    public boolean esMafia() {
        return true;
    }

    @Override
    public String obtenerResultadoInvestigacion() {
        return "Mafia";
    }
}
