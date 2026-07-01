package edu.fiuba.algo3.modelo.Configuracion;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Roles.*;

import java.util.ArrayList;
import java.util.List;

public class BalanceoJuegoGrande implements EstrategiaBalanceo {

    @Override
    public Mazo crearMazo(int cantidadJugadores)
    {
        List<CartaRol> cartas = new ArrayList<>();
        RolFactory rolFactory = new RolFactory();
        if (cantidadJugadores < 10)
        {
            return null;
        }
        cartas.add(rolFactory.crearCartaMafioso());
        cartas.add(rolFactory.crearCartaMafioso());
        cartas.add(rolFactory.crearCartaPadrino());
        cartas.add(rolFactory.crearCartaMedico());
        cartas.add(rolFactory.crearCartaDetective());
        cartas.add(rolFactory.crearCartaSheriff());

        while (cartas.size() < cantidadJugadores)
        {
            cartas.add(rolFactory.crearCartaCiudadano());
        }
        return new Mazo(cartas);
    }
}
