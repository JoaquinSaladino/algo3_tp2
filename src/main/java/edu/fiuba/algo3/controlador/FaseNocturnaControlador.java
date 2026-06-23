package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Arrays;
import java.util.List;

public class FaseNocturnaControlador {

    @FXML private Label lblTurnoJugador;
    @FXML private Label lblTituloRol;
    @FXML private Label lblInstruccionAccion;
    @FXML private Label lblJugadorElegido;
    @FXML private TilePane panelJugadores;
    @FXML private Button btnAccion;

    private List<String> jugadoresVivos;
    private String candidatoSeleccionado = "";

    private String rolActual = "DETECTIVE"; // Cambiar para probar otros

    @FXML
    public void initialize()
    {
        jugadoresVivos = Arrays.asList("J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8");
        prepararPantalla();
    }

    private void prepararPantalla()
    {
        candidatoSeleccionado = "";
        lblJugadorElegido.setText("...");
        btnAccion.setDisable(true);
        lblTurnoJugador.setText("--- Jugador Oculto ---");

        configurarParaRol(rolActual);
        dibujarGrillaJugadores();
    }

    private void configurarParaRol(String rol)
    {
        switch (rol)
        {
            case "MAFIOSO":
                lblTituloRol.setText("TURNO DE LA MAFIA");
                lblInstruccionAccion.setText("ELEGÍ A TU VÍCTIMA PARA ELIMINARLA...");
                btnAccion.setText("ELIMINAR");
                btnAccion.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 3; -fx-background-radius: 5;");
                break;

            case "MEDICO":
                lblTituloRol.setText("TURNO DEL MÉDICO");
                lblInstruccionAccion.setText("ELEGÍ A UN JUGADOR PARA PROTEGERLO ESTA NOCHE...");
                btnAccion.setText("PROTEGER");
                btnAccion.setStyle("-fx-background-color: #66b3cc; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 3; -fx-background-radius: 5;");
                break;

            case "DETECTIVE":
                lblTituloRol.setText("TURNO DEL DETECTIVE");
                lblInstruccionAccion.setText("ELEGÍ A UN JUGADOR PARA INVESTIGAR SU VERDADERO ROL...");
                btnAccion.setText("INVESTIGAR");
                btnAccion.setStyle("-fx-background-color: #a06e5d; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 3; -fx-background-radius: 5;");
                break;
        }
    }

    private void dibujarGrillaJugadores()
    {
        panelJugadores.getChildren().clear();

        for (String nombre : jugadoresVivos)
        {
            Button btn = new Button(nombre);
            btn.setPrefSize(180, 60);
            btn.setFont(Font.font("System", FontWeight.BOLD, 16));
            btn.setStyle("-fx-background-color: #2b3e50; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2;");

            btn.setOnAction(e -> {
                candidatoSeleccionado = nombre;
                lblJugadorElegido.setText("#" + nombre);
                btnAccion.setDisable(false);

                panelJugadores.getChildren().forEach(nodo -> nodo.setStyle("-fx-background-color: #2b3e50; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2;"));
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 3;");
            });

            panelJugadores.getChildren().add(btn);
        }
    }

    @FXML
    public void manejarBotonAccion(ActionEvent event)
    {
        System.out.println("El " + rolActual + " ejecutó su acción sobre: " + candidatoSeleccionado);
        // Pasar decision a Juego y avanzar rol segun configuracion
    }
}