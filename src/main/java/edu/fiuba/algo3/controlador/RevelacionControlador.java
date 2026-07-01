package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
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
    private List<Jugador> listaJugadores;

    @FXML
    public void initialize()
    {
        Juego juego = App.getJuego();
        this.listaJugadores = juego.getJugadores();

        indiceActual = 0;
        mostrarJugadorActual();
    }

    private void mostrarJugadorActual()
    {
        Jugador j = listaJugadores.get(indiceActual);
        lblNombre.setText("#" + j.getNombre());
        lblRol.setText(j.verRol());

        switch (j.verRol())
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

            case "MEDICO":
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
        btnSiguiente.setDisable(indiceActual == listaJugadores.size() - 1);
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
        if (indiceActual < listaJugadores.size() - 1)
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