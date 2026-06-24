package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class FaseNocturnaControlador {

    @FXML private Label lblTurnoJugador;
    @FXML private Label lblTituloRol;
    @FXML private Label lblInstruccionAccion;
    @FXML private Label lblJugadorElegido;
    @FXML private TilePane panelJugadores;
    @FXML private Button btnAccion;

    @FXML private VBox overlayTransicion;
    @FXML private Label lblJugadorTransicion;

    @FXML private VBox overlayResultado;
    @FXML private Label lblResultadoTexto;

    private String candidatoSeleccionado = "";
    private Juego juego;

    @FXML
    public void initialize()
    {
        this.juego = App.getJuego();
        System.out.println("Juego controlador: " + juego);
        prepararTurno();
    }

    public void prepararTurno() {
        String nombreActual = juego.getJugadorActual();
        String rolActual = juego.getRolJugadorActual(nombreActual);
        List<String> objetivos = juego.obtenerObjetivos();

        List<String> companeros = juego.obtenerCompanerosJugadorActual();

        lblJugadorTransicion.setText("#" + nombreActual);
        overlayTransicion.setVisible(true);

        candidatoSeleccionado = "";
        lblJugadorElegido.setText("...");
        btnAccion.setDisable(true);
        lblTurnoJugador.setText("#" + nombreActual);

        configurarParaRol(rolActual);
        dibujarGrillaJugadores(rolActual, objetivos, companeros);
    }

    @FXML
    public void manejarBotonEstoyListo(ActionEvent event)
    {
        overlayTransicion.setVisible(false);
    }

    private void configurarParaRol(String rol)
    {
        if (rol == null) return;
        String rolNormalizado = rol.toUpperCase();

        switch (rolNormalizado)
        {
            case "MAFIOSO":
            case "PADRINO":
                lblTituloRol.setText("TURNO DE LA MAFIA");
                lblInstruccionAccion.setText("ELEGÍ A TU VÍCTIMA PARA ELIMINARLA...");
                btnAccion.setText("ELIMINAR");
                btnAccion.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 3; -fx-background-radius: 5;");
                break;

            case "MEDICO":
            case "MÉDICO":
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

            case "CIUDADANO":
            case "SHERIFF":
                lblTituloRol.setText("TURNO DEL " + rolNormalizado);
                lblInstruccionAccion.setText("ESTÁS DURMIENDO PROFUNDAMENTE. NO ESCUCHÁS NADA...");
                btnAccion.setText("PASAR TURNO");
                btnAccion.setStyle("-fx-background-color: #7b8d93; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 3; -fx-background-radius: 5;");
                break;

            default:
                lblTituloRol.setText("TURNO DE: " + rolNormalizado);
                btnAccion.setText("ACCIONAR");
                break;
        }
    }

    private void dibujarGrillaJugadores(String rol, List<String> objetivosValidos, List<String> compañeros)
    {
        panelJugadores.getChildren().clear();

        if (objetivosValidos.isEmpty()) {
            Label lblZzz = new Label("z Z z Z z . . .");
            lblZzz.setFont(Font.font("System", FontWeight.BOLD, 60));
            lblZzz.setTextFill(Color.web("#5a7a94"));
            panelJugadores.getChildren().add(lblZzz);
            btnAccion.setDisable(false);
            return;
        }

        if ((rol.equalsIgnoreCase("MAFIOSO") || rol.equalsIgnoreCase("PADRINO")) && compañeros != null && !compañeros.isEmpty())
        {
            Label lblCompas = new Label("Tus compañeros son: " + String.join(", ", compañeros));
            lblCompas.setFont(Font.font("System", FontWeight.BOLD, 18));
            lblCompas.setTextFill(Color.web("#cc0000"));
            lblCompas.setPadding(new Insets(0, 0, 20, 0));
            panelJugadores.getChildren().add(lblCompas);
        }

        for (String nombre : objetivosValidos)
        {
            Button btn = new Button(nombre);
            btn.setPrefSize(180, 60);
            btn.setFont(Font.font("System", FontWeight.BOLD, 16));
            btn.setStyle("-fx-background-color: #2b3e50; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2;");

            btn.setOnAction(e -> {
                candidatoSeleccionado = nombre;
                lblJugadorElegido.setText("#" + nombre);
                btnAccion.setDisable(false);

                panelJugadores.getChildren().stream()
                        .filter(nodo -> nodo instanceof Button)
                        .forEach(nodo -> nodo.setStyle("-fx-background-color: #2b3e50; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2;"));

                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 3;");
            });
            panelJugadores.getChildren().add(btn);
        }
    }

    @FXML
    public void manejarBotonAccion(ActionEvent event) {
        String nombre = juego.getJugadorActual();
        String rol = juego.getRolJugadorActual(nombre);
        boolean tieneAccion = !rol.equals("Ciudadano") && !rol.equals("Sheriff");

        if (tieneAccion && candidatoSeleccionado.isEmpty()) return;

        if(tieneAccion) {
            juego.seleccionarObjetivo(candidatoSeleccionado);
            if (rol.equals("Detective")) {
                String resultado = juego.obtenerResultadoInvestigacion(nombre, candidatoSeleccionado);
                lblResultadoTexto.setText(candidatoSeleccionado + " es " + resultado);
                overlayResultado.setVisible(true);
                return;
            }
        }
        manejarContinuar(event);
//
//        boolean haySiguiente = juego.avanzarJugadorActual();
//        if (haySiguiente) {
//            prepararTurno();
//        } else {
//            juego.ejecutarFaseActual();
//            System.out.println(juego.obtenerResultadoFase());
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaFaseDiurna.fxml"));
//                Parent root = loader.load();
//                Stage stage = (Stage) btnAccion.getScene().getWindow();
//                stage.setScene(new Scene(root, 1000, 600));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @FXML
    public void manejarContinuar(ActionEvent event) {
        overlayResultado.setVisible(false);

        boolean haySiguiente = juego.avanzarJugadorActual();
        if (haySiguiente) {
            prepararTurno();
        } else {
            juego.ejecutarFaseActual();
            System.out.println(juego.obtenerResultadoFase());
            juego.avanzarFase();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaFaseDiurna.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnAccion.getScene().getWindow();
                stage.setScene(new Scene(root, 1000, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

