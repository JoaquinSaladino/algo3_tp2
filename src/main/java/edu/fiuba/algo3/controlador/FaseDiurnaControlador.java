package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Arrays;
import java.util.List;

public class FaseDiurnaControlador {

    @FXML private Label lblTurnoJugador;
    @FXML private Label lblJugadorElegido;
    @FXML private TilePane panelJugadores;
    @FXML private Button btnVotar;

    private List<String> jugadoresVivos;
    private int indiceTurnoActual = 0;
    private String candidatoSeleccionado = "";

    @FXML
    public void initialize()
    {
        jugadoresVivos = Arrays.asList("J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8");
        prepararTurno();
    }

    private void prepararTurno() {

        candidatoSeleccionado = "";
        lblJugadorElegido.setText("...");
        btnVotar.setDisable(true);

        String jugadorActual = jugadoresVivos.get(indiceTurnoActual);
        lblTurnoJugador.setText("#" + jugadorActual);

        panelJugadores.getChildren().clear();

        for (String nombre : jugadoresVivos)
        {
            Button btnJugador = crearBotonJugador(nombre);

            if (nombre.equals(jugadorActual))
            {
                btnJugador.setDisable(true);
            }

            panelJugadores.getChildren().add(btnJugador);
        }
    }

    private Button crearBotonJugador(String nombre)
    {
        Button btn = new Button(nombre);
        btn.setPrefSize(180, 60);
        btn.setFont(Font.font("System", FontWeight.BOLD, 16));
        btn.setStyle("-fx-background-color: #4a6572; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 2;");

        btn.setOnAction(e -> {
            candidatoSeleccionado = nombre;
            lblJugadorElegido.setText("#" + nombre);
            btnVotar.setDisable(false);

            panelJugadores.getChildren().forEach(nodo -> nodo.setStyle("-fx-background-color: #4a6572; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 2;"));
            btn.setStyle("-fx-background-color: #fca311; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 3;");
        });

        return btn;
    }

    @FXML
    public void manejarBotonVotar(ActionEvent event)
    {
        System.out.println(jugadoresVivos.get(indiceTurnoActual) + " votó por: " + candidatoSeleccionado);

        indiceTurnoActual++;

        if (indiceTurnoActual < jugadoresVivos.size())
        {
            prepararTurno();
        }

        else
        {
            System.out.println("¡Fin de la votación! Procesando eliminados...");
            // Conectar con pantalla de resultados
        }
    }
}