package org.example.taller2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.taller2.Hilo.Ventana;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/taller2/Vehiculo.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Sistema Vehículos");
        stage.setScene(scene);
        stage.show();

        // HILO (como te piden)
        new Ventana().start();
    }
}