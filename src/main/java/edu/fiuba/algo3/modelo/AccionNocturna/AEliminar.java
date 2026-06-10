package edu.fiuba.algo3.modelo.AccionNocturna;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public class AEliminar extends AccionNocturna {

    public AEliminar(Jugador autor, Jugador objetivo){
        super(autor,objetivo);
    }

    @Override
    public void resolver() {
        if (!this.objetivo.estaProtegido()) {
            this.objetivo.eliminar();
        }
    }
    @Override
    public void insertarEn(List<AccionNocturna> acciones) {
        acciones.add(this);
    }

}
