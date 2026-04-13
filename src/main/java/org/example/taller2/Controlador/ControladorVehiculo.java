package org.example.taller2.Controlador;

import org.example.taller2.Conexion.ConexionMongo;
import org.example.taller2.Modelo.ModeloVehiculo;
import org.example.taller2.Hilo.HiloVehiculo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ControladorVehiculo {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtPrecio;

    private ConexionMongo conexion = new ConexionMongo();

    // BOTÓN GUARDAR COMPLETO
    @FXML
    public void guardar() {
        try {
            // 1. Crear objeto
            ModeloVehiculo modelo = new ModeloVehiculo();

            modelo.setCodigo(txtCodigo.getText());
            modelo.setMarca(txtMarca.getText());
            modelo.setModelo(txtModelo.getText());
            modelo.setPrecio(Double.parseDouble(txtPrecio.getText()));

            // 2. HILO (guardar + buscar + mostrar)
            Thread hilo = new Thread(() -> {

                // A) Guardar en Mongo
                conexion.guardarVehiculo(modelo);

                // B) Buscar lo que guardaste
                ModeloVehiculo encontrado = conexion.buscarPorCodigo(modelo.getCodigo());

                // C) Volver al hilo UI
                Platform.runLater(() -> {

                    if (encontrado != null) {

                        mensaje(
                                "Vehículo guardado en Mongo:\n\n" +
                                        "Código: " + encontrado.getCodigo() + "\n" +
                                        "Marca: " + encontrado.getMarca() + "\n" +
                                        "Modelo: " + encontrado.getModelo() + "\n" +
                                        "Precio: " + encontrado.getPrecio()
                        );

                    } else {
                        mensaje("Se guardó, pero no se pudo recuperar.");
                    }

                    limpiarCampos();
                });
            });

            hilo.start();

            // Hilo adicional que te pide la práctica
            new HiloVehiculo(modelo).start();

        } catch (NumberFormatException e) {
            mensaje("El precio debe ser numérico");
        }
    }
    @FXML
    public void editar() {
        try {
            ModeloVehiculo modelo = new ModeloVehiculo();

            modelo.setCodigo(txtCodigo.getText());
            modelo.setMarca(txtMarca.getText());
            modelo.setModelo(txtModelo.getText());
            modelo.setPrecio(Double.parseDouble(txtPrecio.getText()));

            Thread hilo = new Thread(() -> {

                conexion.editarVehiculo(modelo);

                Platform.runLater(() -> {
                    mensaje("Vehículo actualizado correctamente");
                    limpiarCampos();
                });
            });

            hilo.start();

        } catch (NumberFormatException e) {
            mensaje("El precio debe ser numérico");
        }
    }

    //LIMPIAR
    private void limpiarCampos() {
        txtCodigo.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtPrecio.clear();
    }

    // MENSAJE
    private void mensaje(String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(texto);
        alert.show();
    }

}