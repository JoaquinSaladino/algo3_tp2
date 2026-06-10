package edu.fiuba.algo3.modelo.Configuracion;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Roles.*;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Ciudadano;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Detective;
import edu.fiuba.algo3.modelo.Roles.Ciudadanos.Medico;
import edu.fiuba.algo3.modelo.Roles.Mafiosos.Mafioso;

import java.util.ArrayList;
import java.util.List;

public class BalanceoJuegoMediano implements EstrategiaBalanceo {
    public Mazo crearMazo(int cantidadJugadores)
    {
        List<CartaRol> cartas = new ArrayList<>();

        if (cantidadJugadores == 7)
        {
            cartas.add(new Mafioso());
            cartas.add(new Mafioso());
            cartas.add(new Detective());
            cartas.add(new Medico());
        }

        else if (cantidadJugadores == 8)
        {
            cartas.add(new Mafioso());
            cartas.add(new Mafioso());
            cartas.add(new Detective());
            cartas.add(new Medico());
        }

        else if (cantidadJugadores == 9)
        {
            cartas.add(new Mafioso());
            cartas.add(new Mafioso());
            cartas.add(new Mafioso());
            cartas.add(new Detective());
            cartas.add(new Medico());
        }

        while (cartas.size() < cantidadJugadores)
        {
            cartas.add(new Ciudadano());
        }

        return new Mazo(cartas);
    }
}
