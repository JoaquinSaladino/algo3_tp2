package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Roles.CartaRol;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private boolean vivo;
    private CartaRol carta;
    private boolean protegido;

    private List<Jugador> companerosMafia;

    public Jugador(String nombre)
    {
        this.nombre = nombre;
        this.vivo = true;
        this.protegido = false;
        this.companerosMafia = new ArrayList<>();
    }

    public void proteger()
    {
        this.protegido = true;
    }

    public void eliminar()
    {
        this.vivo = false;
    }

    public void usarHabilidad(Jugador objetivo)
    {
        this.carta.ejecutarAccionNocturna(objetivo);
    }

    public boolean esMafia()
    {
        return this.carta.esMafia();
    }

    public void registrarCompaneros(List<Jugador> companeros)
    {
        this.companerosMafia = companeros;
    }

    public boolean conoceA(Jugador otroJugador)
    {
        return this.companerosMafia.contains(otroJugador);
    }

}