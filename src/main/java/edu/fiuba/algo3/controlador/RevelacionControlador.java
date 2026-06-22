package edu.fiuba.algo3.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.Arrays;
import java.util.List;

public class RevelacionControlador {

    @FXML private Label lblNombre;
    @FXML private Label lblRol;
    @FXML private Button btnAnterior;
    @FXML private Button btnSiguiente;

    private int indiceActual = 0;

    // Temporal para probar vista
    private static class JugadorMock
    {
        String nombre; String rol;
        JugadorMock(String n, String r) { this.nombre = n; this.rol = r; }
    }

    // Esta lista es una simulacion de la que nos daria la clase Juego ya ordenada
    private List<JugadorMock> jugadoresOrdenados;

    @FXML
    public void initialize()
    {
        jugadoresOrdenados = Arrays.asList(
                new JugadorMock("J1", "PADRINO"),
                new JugadorMock("J2", "MAFIOSO"),
                new JugadorMock("J3", "DETECTIVE"),
                new JugadorMock("J4", "MÉDICO"),
                new JugadorMock("J5", "SHERIFF"),
                new JugadorMock("J6", "CIUDADANO"),
                new JugadorMock("J7", "CIUDADANO")
        );

        indiceActual = 0;
        mostrarJugadorActual();
    }

    private void mostrarJugadorActual()
    {
        JugadorMock j = jugadoresOrdenados.get(indiceActual);
        lblNombre.setText("#" + j.nombre);
        lblRol.setText(j.rol);

        switch (j.rol)
        {
            case "PADRINO":
                lblRol.setTextFill(Color.web("#8b0000"));
                break;

            case "MAFIOSO":
                lblRol.setTextFill(Color.web("#cc0000"));
                break;

            case "DETECTIVE":
                lblRol.setTextFill(Color.web("#a06e5d"));
                break;

            case "MÉDICO":
                lblRol.setTextFill(Color.web("#66b3cc"));
                break;

            case "SHERIFF":
                lblRol.setTextFill(Color.web("#dca026"));
                break;

            default:
                lblRol.setTextFill(Color.WHITE);
                break;
        }

        btnAnterior.setDisable(indiceActual == 0);
        btnSiguiente.setDisable(indiceActual == jugadoresOrdenados.size() - 1);
    }

    @FXML
    public void manejarAnterior(ActionEvent event)
    {
        if (indiceActual > 0)
        {
            indiceActual--;
            mostrarJugadorActual();
        }
    }

    @FXML
    public void manejarSiguiente(ActionEvent event)
    {
        if (indiceActual < jugadoresOrdenados.size() - 1)
        {
            indiceActual++;
            mostrarJugadorActual();
        }
    }

    @FXML
    public void manejarBotonSalir(ActionEvent event)
    {
        Platform.exit();
    }
}