package org.example.taller2.Hilo;

public class Ventana extends Thread {
   @Override
    public void run() {
       while (true) {
           System.out.println("Ventana activa");
           try {
               Thread.sleep(5000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }
}


