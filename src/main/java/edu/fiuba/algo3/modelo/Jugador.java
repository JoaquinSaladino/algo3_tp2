package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Excepciones.RolNoVisibleException;
import edu.fiuba.algo3.modelo.Roles.CartaRol;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public boolean esMismoNombre(String nombre){
        return Objects.equals(this.nombre, nombre);
    }

    public void proteger()
    {
        this.protegido = true;
    }

    public void desproteger(){
        this.protegido=false;
    }

    public void eliminar()
    {
        if (!this.protegido){
            this.vivo = false;
        }
    }

    public AccionNocturna usarHabilidad(Jugador objetivo) {
        if(!this.estaVivo())
            throw new ObjetivoInvalidoException();
        return this.carta.generarAccionNocturna(this, objetivo);
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
        return this.carta.obtenerRol();
    }

    public CartaRol verCartaDe(Jugador otroJugador)
    {
        if (!otroJugador.estaVivo()) {
            return otroJugador.obtenerCarta();
        }
        throw new RolNoVisibleException();
    }

    public boolean estaVivo() { return vivo; }

    public boolean estaProtegido() { return protegido; }

    public Jugador obtenerObjetivoElegido(){
        return null;
    }
}