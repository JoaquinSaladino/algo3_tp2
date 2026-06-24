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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FaseDiurnaControlador {

    @FXML private Label lblTurnoJugador;
    @FXML private Label lblJugadorElegido;
    @FXML private Label lblResultadoTexto;
    @FXML private TilePane panelJugadores;
    @FXML private Button btnVotar;
    @FXML private StackPane overlayResultado;

    private List<String> jugadoresVivos;
    private int indiceTurnoActual = 0;
    private String candidatoSeleccionado = "";
    private Juego juego;

    @FXML
    public void initialize()
    {
        juego = App.getJuego();
        jugadoresVivos = juego.getJugadores()
                .stream()
                .filter(Jugador::estaVivo)
                .map(Jugador::getNombre)
                .collect(Collectors.toList());
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
        juego.seleccionarObjetivo(candidatoSeleccionado);

        indiceTurnoActual++;

        if (indiceTurnoActual < jugadoresVivos.size())
        {
            prepararTurno();
        }

        else
        {
            System.out.println("¡Fin de la votación! Procesando eliminados...");
            juego.ejecutarFaseActual();
            juego.avanzarFase();

            String resultado = juego.obtenerResultadoFase();

            lblResultadoTexto.setText(resultado);

            overlayResultado.setVisible(true);
            // Conectar con pantalla de resultados
        }
    }

    @FXML
    public void manejarContinuar(ActionEvent event)
    {
        juego.avanzarFase();

        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/vista/pantallaFaseNocturna.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) btnVotar.getScene().getWindow();

            stage.setScene(new Scene(root,1000,600));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}