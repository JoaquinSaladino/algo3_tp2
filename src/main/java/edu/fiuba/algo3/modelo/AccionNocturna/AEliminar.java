package edu.fiuba.algo3.modelo.AccionNocturna;

import java.util.List;

public class AEliminar extends AccionNocturna {
    @Override
    public void resolver() {

    }
    @Override
    public void insertarEn(List<AccionNocturna> acciones) {
        acciones.add(this);
    }

}
