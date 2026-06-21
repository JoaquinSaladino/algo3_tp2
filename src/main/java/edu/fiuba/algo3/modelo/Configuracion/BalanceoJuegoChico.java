package edu.fiuba.algo3.modelo.Configuracion;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Roles.*;


import java.util.ArrayList;
import java.util.List;

public class BalanceoJuegoChico implements EstrategiaBalanceo {
    public Mazo crearMazo(int cantidadJugadores)
    {
        List<CartaRol> cartas = new ArrayList<>();
        RolFactory rolFactory = new RolFactory();
        if (cantidadJugadores == 5)
        {
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaDetective());
        }

        else if (cantidadJugadores == 6)
        {
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMafioso());
            cartas.add(rolFactory.crearCartaMedico());
        }

        while (cartas.size() < cantidadJugadores)
        {
            cartas.add(rolFactory.crearCartaCiudadano());
        }

        return new Mazo(cartas);
    }
}
