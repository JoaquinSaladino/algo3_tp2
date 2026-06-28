package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
public class ResultadoDiurnoControlador {
    @FXML
    private Label lblResultado;
    @FXML
    private Button btnContinuar;
    private Juego juego;

    @FXML
    public void initialize() {
        this.juego = App.getJuego();
        String resumen = juego.obtenerResultadoFase();
        lblResultado.setText(resumen);
    }

    @FXML
    public void manejarBotonContinuar(ActionEvent event) {
        if (juego.juegoTerminado()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaFinDePartida.fxml"));
                Stage stage = (Stage) btnContinuar.getScene().getWindow();
                stage.setScene(new Scene(loader.load(), 1000, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            juego.avanzarFase();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaFaseNocturna.fxml"));
                Stage stage = (Stage) btnContinuar.getScene().getWindow();
                stage.setScene(new Scene(loader.load(), 1000, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}