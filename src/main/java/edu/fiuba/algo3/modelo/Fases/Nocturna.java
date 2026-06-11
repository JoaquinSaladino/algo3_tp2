package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.AccionNocturna.AccionNocturna;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Nocturna implements Fase {
    private List<AccionNocturna> accionNocturnas;
    public Nocturna(){
        this.accionNocturnas = new ArrayList<>();
    }
    @Override
    public void ejecutar(List<Jugador> jugadores) {
        // 1. RECOLECCIÓN Y ORDENAMIENTO AUTOMÁTICO
        for (Jugador jugador : jugadores) {
            // Cada jugador genera su acción según a quién eligió
            // (En el juego real, el objetivo vendrá seteado previamente por la UI/Controlador)
            Jugador objetivo = jugador.obtenerObjetivoElegido();

            if (objetivo != null) {
                AccionNocturna accion = jugador.usarHabilidad(objetivo);

                // Acá usás tu doble despacho: cada acción sabe dónde ponerse.
                // AProteger se insertará al principio (índice 0), AEliminar al final.
                accion.insertarEn(this.accionNocturnas);
            }
        }

        // 2. RESOLUCIÓN EN ORDEN
        for (AccionNocturna accion : accionNocturnas) {
            accion.resolver();
        }
    }
}
