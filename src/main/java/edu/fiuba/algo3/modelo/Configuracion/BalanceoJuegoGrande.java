package edu.fiuba.algo3.modelo.Configuracion;

import edu.fiuba.algo3.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class BalanceoJuegoGrande implements EstrategiaBalanceo {

    @Override
    public Mazo crearMazo(int cantidadJugadores)
    {
        List<CartaRol> cartas = new ArrayList<>();

        if (cantidadJugadores == 10)
        {
            cartas.add(new Mafioso());
            cartas.add(new Mafioso());
            cartas.add(new Padrino());
            cartas.add(new Detective());
            cartas.add(new Medico());
            cartas.add(new Sheriff());
        }

        else if (cantidadJugadores == 11)
        {
            cartas.add(new Mafioso());
            cartas.add(new Mafioso());
            cartas.add(new Padrino());
            cartas.add(new Detective());
            cartas.add(new Medico());
            cartas.add(new Sheriff());
        }

        else if (cantidadJugadores == 12)
        {
            cartas.add(new Mafioso());
            cartas.add(new Mafioso());
            cartas.add(new Padrino());
            cartas.add(new Detective());
            cartas.add(new Medico());
            cartas.add(new Sheriff());
        }

        while (cartas.size() < cantidadJugadores)
        {
            cartas.add(new Ciudadano());
        }

        return new Mazo(cartas);
    }
}
