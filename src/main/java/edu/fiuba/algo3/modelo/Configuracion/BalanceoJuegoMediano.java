package edu.fiuba.algo3.modelo.Configuracion;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Roles.*;

import java.util.ArrayList;
import java.util.List;

public class BalanceoJuegoMediano implements EstrategiaBalanceo {
    public Mazo crearMazo(int cantidadJugadores)
    {
        List<CartaRol> cartas = new ArrayList<>();
        RolFactory rolFactory = new RolFactory();
        if (cantidadJugadores == 7)
        {
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMedico());
            cartas.add(rolFactory.crearCartaDetective());
        }

        else if (cantidadJugadores == 8)
        {
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMedico());
            cartas.add(rolFactory.crearCartaDetective());
        }

        else if (cantidadJugadores == 9)
        {
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMedico());
            cartas.add(rolFactory.crearCartaDetective());
        }

        while (cartas.size() < cantidadJugadores)
        {
            cartas.add(rolFactory.crearCartaCiudadano());
        }

        return new Mazo(cartas);
    }
}
