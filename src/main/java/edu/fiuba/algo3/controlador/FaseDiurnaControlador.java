package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.text.LabelView;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FaseDiurnaControlador {

    @FXML private Label lblTurnoJugador;
    @FXML private Label lblJugadorElegido;
    @FXML private TilePane panelJugadores;
    @FXML private Button btnVotar;
    @FXML private Label lblInstruccion;

    private List<String> jugadoresVivos;
    private int indiceTurnoActual = 0;
    private String candidatoSeleccionado = "";
    private Juego juego;
    @FXML
    public void initialize()
    {
        this.juego = App.getJuego();
        this.jugadoresVivos = juego.getJugadores().stream()
                .filter(Jugador::estaVivo)
                .map(Jugador::getNombre)
                .collect(Collectors.toList());
        prepararTurno();

    }

    private void prepararTurno() {

        candidatoSeleccionado = "";
        lblJugadorElegido.setText("...");
        btnVotar.setDisable(true);

        String jugadorActual = juego.getJugadorActual();
        lblTurnoJugador.setText("#" + jugadorActual);

        if(juego.estaEnNominacion()) {
            lblInstruccion.setText("NOMINA A UN JUGADOR...");
            btnVotar.setText("NOMINAR");
        } else {
            lblInstruccion.setText("VOTA A UN JUGADOR...");
            btnVotar.setText("VOTAR");
        }

        panelJugadores.getChildren().clear();
        for (String nombre : juego.obtenerObjetivos())
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
        System.out.println(juego.getJugadorActual() + " votó por: " + candidatoSeleccionado);
        juego.seleccionarObjetivo(candidatoSeleccionado);

        boolean haySiguiente = juego.avanzarJugadorActual();
        if (haySiguiente)
        {
            prepararTurno();
        }

        else
        {
            juego.ejecutarFaseActual();
            System.out.println(juego.obtenerResultadoFase());
            try {
                if(juego.juegoTerminado()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaFinDePartida.fxml"));
                    Stage stage = (Stage) btnVotar.getScene().getWindow();
                    stage.setScene(new Scene(loader.load(), 1000, 600));
                } else {
                    juego.avanzarFase();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaFaseNocturna.fxml"));
                    Stage stage = (Stage) btnVotar.getScene().getWindow();
                    stage.setScene(new Scene(loader.load(), 1000, 600));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}