package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Desempate.MecanismoDesempate;

import java.util.*;

import java.util.stream.Collectors;

public class Votacion {

    private List<Jugador> nominados;
    private Map<Jugador,Integer> votos;
    private MecanismoDesempate desempate;

    public Votacion(MecanismoDesempate desempate) {
        this.desempate = desempate;
        this.votos = new HashMap<>();
    }

    public void iniciar(List<Jugador> nominados) {

        this.nominados = nominados;
        this.votos.clear();

        for (Jugador jugador : nominados) {
            votos.put(jugador,0);
        }
    }

    public void registrarVoto(Jugador votante, Jugador nominado) {

        if (!votos.containsKey(nominado))
            return;

        votos.put(
                nominado,
                votos.get(nominado) + 1
        );
    }

    public Jugador resolverVotacion() {

        int maximo = votos.values()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);

        List<Jugador> empatados = votos.entrySet()
                .stream()
                .filter(e -> e.getValue() == maximo)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (empatados.size() == 1)
            return empatados.get(0);

        return desempate.resolver(empatados);
    }

    public boolean hayEmpate() {

        int maximo = votos.values()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);

        return votos.values()
                .stream()
                .filter(v -> v == maximo)
                .count() > 1;
    }
}
