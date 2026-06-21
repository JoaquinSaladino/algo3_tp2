package edu.fiuba.algo3.modelo.Configuracion;

import edu.fiuba.algo3.modelo.Mazo;

public class ConfiguracionPartida {

    private final int cantidadJugadores;

    public ConfiguracionPartida(int cantidadJugadores)
    {
        if (cantidadJugadores < 5 || cantidadJugadores > 12)
        {
            throw new IllegalArgumentException("La cantidad de jugadores debe ser entre 5 y 12");
        }

        this.cantidadJugadores = cantidadJugadores;
    }

    public Mazo generarMazo()
    {
        EstrategiaBalanceo estrategia;

        if (this.cantidadJugadores <= 6)
        {
            estrategia = new BalanceoJuegoChico();
        }

        else if (this.cantidadJugadores <= 9)
        {
            estrategia = new BalanceoJuegoMediano();
        }

        else
        {
            estrategia = new BalanceoJuegoGrande();
        }

        return estrategia.crearMazo(this.cantidadJugadores);
    }
}
