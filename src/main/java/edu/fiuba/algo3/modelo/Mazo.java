package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Excepciones.MazoVacioException;
import edu.fiuba.algo3.modelo.Roles.CartaRol;

import java.util.Collections;
import java.util.List;

public class Mazo {

    private List<CartaRol> cartas;

    public Mazo(List<CartaRol> cartas)
    {
        this.cartas = cartas;
    }

    public boolean estaVacio()
    {
        return this.cartas.isEmpty();
    }

    public void mezclar()
    {
        Collections.shuffle(this.cartas);
    }

    public CartaRol repartir()
    {
        if (this.estaVacio())
        {
            throw new MazoVacioException();
        }
        return this.cartas.remove(0);

    }
}
