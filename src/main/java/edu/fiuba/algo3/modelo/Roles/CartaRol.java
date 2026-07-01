package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Bando.Bando;
import edu.fiuba.algo3.modelo.Excepciones.ObjetivoInvalidoException;
import edu.fiuba.algo3.modelo.Habilidades.HabilidadNocturna;
import edu.fiuba.algo3.modelo.Jugador;

public class CartaRol  {
    private final HabilidadNocturna habilidadNocturna;
    private final Bando bando;
    private final String rol;


    public CartaRol(HabilidadNocturna habilidad , Bando bando , String rol)
    {
        this.habilidadNocturna = habilidad;
        this.bando = bando;
        this.rol = rol;
    }

    public AccionNocturna generarAccionNocturna(Jugador autor , Jugador objetivo)
    {
        return this.habilidadNocturna.ejecutar(autor,objetivo);
    }

    public boolean esMafia(){
        return bando.esMafia();
    };

    public String investigar(){
        return bando.obtenerResultadoInvestigacion();
    }

    public String getRol() {
        return rol;
    }

    public boolean esObjetivoValido(Jugador jugador, Jugador posibleObjetivo) {
        try {
            habilidadNocturna.validarObjetivo(jugador,posibleObjetivo);
            return true;
        }catch (ObjetivoInvalidoException e){

            return false;
        }
    }
}