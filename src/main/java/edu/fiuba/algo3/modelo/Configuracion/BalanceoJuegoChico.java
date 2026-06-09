package edu.fiuba.algo3.modelo.Configuracion;

import edu.fiuba.algo3.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class BalanceoJuegoChico implements EstrategiaBalanceo {
    public Mazo crearMazo(int cantidadJugadores)
    {
        List<CartaRol> cartas = new ArrayList<>();

        if (cantidadJugadores == 5)
        {
            cartas.add(new Mafioso());
            cartas.add(new Detective());
        }

        else if (cantidadJugadores == 6)
        {
            cartas.add(new Mafioso());
            cartas.add(new Mafioso());
            cartas.add(new Medico());
        }

        while (cartas.size() < cantidadJugadores)
        {
            cartas.add(new Ciudadano());
        }

        return new Mazo(cartas);
    }
}
