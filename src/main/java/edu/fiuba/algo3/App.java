package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Juego;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Juego juegoActual;

    public static Juego getJuego()
    {
        if (juegoActual == null)
        {
            juegoActual = new Juego();
        }

        return juegoActual;
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaInicio.fxml"));
        Parent root = loader.load();

        Scene escena = new Scene(root, 1000, 600);

        escenarioPrincipal.setTitle("Mafia - El Juego");
        escenarioPrincipal.setScene(escena);

        escenarioPrincipal.setResizable(false);

        escenarioPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}