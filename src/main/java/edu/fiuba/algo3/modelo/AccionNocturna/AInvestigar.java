package edu.fiuba.algo3.modelo.AccionNocturna;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.RegistroNocturno;

import java.util.List;

public class AInvestigar extends AccionNocturna {

    public AInvestigar(Jugador autor, Jugador objetivo){
        super(autor,objetivo);
    }

    @Override
    public void resolver(RegistroNocturno registroActual) {
        registroActual.registrarInvestigacion(this.autor, this.objetivo);
    }

}
