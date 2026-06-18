package edu.fiuba.algo3.controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioControlador {

    @FXML
    private Button botonJugar;

    @FXML
    private Button botonSalir;

    @FXML
    public void manejarBotonJugar()
    {
        System.out.println("Cambio de escena a eleccion de tipo de partida...");
    }

    @FXML
    public void manejarBotonSalir()
    {
        Platform.exit();
    }
}