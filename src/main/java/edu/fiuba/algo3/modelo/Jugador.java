package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Excepciones.RolNoVisibleException;
import edu.fiuba.algo3.modelo.Roles.CartaRol;


import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private boolean vivo;
    private CartaRol carta;
    private boolean protegido;

    private List<Jugador> companeros;

    public Jugador(String nombre)
    {
        this.nombre = nombre;
        this.vivo = true;
        this.protegido = false;
        this.companeros = new ArrayList<>();
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
        this.companeros = companeros;
    }

    public boolean conoceA(Jugador otroJugador)
    {
        return this.companeros.contains(otroJugador);
    }

    public void asignarCarta(CartaRol carta) { this.carta = carta; }

    public CartaRol obtenerCarta() { return this.carta; }

    public String verRol()
    {
        return this.carta.obtenerNombre();
    }

    public CartaRol verCartaDe(Jugador otroJugador)
    {
        throw new RolNoVisibleException();
    }

    public boolean estaVivo() { return vivo; }

    public boolean estaProtegido() { return protegido; }

}