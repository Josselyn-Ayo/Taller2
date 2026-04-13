package org.example.taller2.Hilo;

import org.example.taller2.Modelo.ModeloVehiculo;

public class HiloVehiculo extends Thread {
    private ModeloVehiculo modelo;
    public HiloVehiculo(ModeloVehiculo modelo) {
        this.modelo = modelo;
    }
    @Override
    public void run() {
        System.out.println("---- VEHÍCULO----");
        System.out.println("Código:" + modelo.getCodigo());
        System.out.println("Marca:"+ modelo.getMarca());
        System.out.println("Modelo:" + modelo.getModelo());
        System.out.println("Precio:" + modelo.getPrecio());
}

}
