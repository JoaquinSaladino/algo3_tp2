package edu.fiuba.algo3.modelo.Roles;

import edu.fiuba.algo3.modelo.Bando.BandoCiudadano;
import edu.fiuba.algo3.modelo.Bando.BandoMafia;
import edu.fiuba.algo3.modelo.Bando.BandoPadrino;
import edu.fiuba.algo3.modelo.Habilidades.Investigar;
import edu.fiuba.algo3.modelo.Habilidades.Nula;
import edu.fiuba.algo3.modelo.Habilidades.Proteger;
import edu.fiuba.algo3.modelo.Habilidades.Seleccionar;

public class RolFactory {
    public CartaRol crearCartaCiudadano(){
        return new CartaRol(new Nula(), new BandoCiudadano() , "Ciudadano");
    }

    public CartaRol crearCartaMedico(){
        return new CartaRol(new Proteger(), new BandoCiudadano() , "Medico");
    }
    public CartaRol crearCartaDetective(){
        return new CartaRol(new Investigar(), new BandoCiudadano() , "Detective");
    }
    public CartaRol crearCartaSheriff(){
        return new CartaRol(new Nula(), new BandoCiudadano() , "Sheriff");
    }
    public CartaRol crearCartaMafioso(){
        return new CartaRol(new Seleccionar(), new BandoMafia(), "Mafioso");
    }
    public CartaRol crearCartaPadrino(){
        return new CartaRol(new Seleccionar(), new BandoPadrino(), "Padrino");
    }
}
