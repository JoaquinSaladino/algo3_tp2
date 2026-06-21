package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Roles.CartaRol;

import java.util.*;

public class RegistroNocturno {
    private Map<Jugador, List<CartaRol>> votosMafia = new HashMap<>();
    private Map<Jugador, String> resultadosInvestigacion = new HashMap<>();
    private Jugador ultimaInvestigacion;

    public void registrarVotoMafia(Jugador objetivo, CartaRol votante) {
        this.votosMafia.putIfAbsent(objetivo, new ArrayList<>());
        this.votosMafia.get(objetivo).add(votante);
    }

    public Jugador obtenerMasVotadoPorLaMafia() {
        if (votosMafia.isEmpty()) {
            return null;
        }

        Jugador elegido = null;
        int maxVotos = -1;
        boolean hayEmpate = false;

        for (Map.Entry<Jugador, List<CartaRol>> entry : votosMafia.entrySet()) {
            int cantidadVotos = entry.getValue().size();
            if (cantidadVotos > maxVotos) {
                maxVotos = cantidadVotos;
                elegido = entry.getKey();
                hayEmpate = false;
            } else if (cantidadVotos == maxVotos) {
                hayEmpate = true;
            }
        }

        if (hayEmpate) {
            elegido = resolverEmpatePorPadrino(maxVotos);
        }

        return elegido;
    }

    private Jugador resolverEmpatePorPadrino(int maxVotos) {
        Jugador elegidoPorPadrino = null;
        int candidatosConPadrino = 0;

        for (Map.Entry<Jugador, List<CartaRol>> entry : votosMafia.entrySet()) {
            if (entry.getValue().size() == maxVotos) {
                if (contieneAlPadrino(entry.getValue())) {
                    elegidoPorPadrino = entry.getKey();
                    candidatosConPadrino++;
                }
            }
        }

        if (candidatosConPadrino == 1) {
            return elegidoPorPadrino;
        }

        return null;
    }

    private boolean contieneAlPadrino(List<CartaRol> votantes) {
        for (CartaRol v : votantes) {
            if (Objects.equals(v.getRol(), "Padrino")) {
                return true;
            }
        }
        return false;
    }

    public void registrarInvestigacion(Jugador investigador, Jugador investigado) {
        this.resultadosInvestigacion.put(investigador, investigado.obtenerCarta().investigar());
        this.ultimaInvestigacion = investigado;
    }

    public String obtenerResultadoInvestigacion(Jugador investigador) {
        return this.resultadosInvestigacion.get(investigador);
    }

    public Jugador obtenerUltimaInvestigacion(){
        return ultimaInvestigacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroNocturno that = (RegistroNocturno) o;
        return Objects.equals(votosMafia, that.votosMafia) &&
                Objects.equals(resultadosInvestigacion, that.resultadosInvestigacion) &&
                Objects.equals(ultimaInvestigacion, that.ultimaInvestigacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(votosMafia, resultadosInvestigacion, ultimaInvestigacion);
    }
}
