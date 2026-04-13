package org.example.taller2.Conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.example.taller2.Modelo.ModeloVehiculo;
import org.bson.Document;

public class ConexionMongo {

    private static final String URI =
            "mongodb+srv://Anabel2016:Dios1234@cluster0.jggd4tq.mongodb.net/?appName=Cluster0";

    private MongoCollection<Document> getCollection() {
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase database = mongoClient.getDatabase("esfotcarDB");
        return database.getCollection("vehiculos");
    }

    public void guardarVehiculo(ModeloVehiculo vehiculo) {
        try {
            MongoCollection<Document> collection = getCollection();

            Document doc = new Document("codigo", vehiculo.getCodigo())
                    .append("marca", vehiculo.getMarca())
                    .append("modelo", vehiculo.getModelo())
                    .append("precio", vehiculo.getPrecio());

            collection.insertOne(doc);

            System.out.println("Vehículo guardado correctamente");

        } catch (Exception e) {
            System.out.println("Error guardar: " + e.getMessage());
        }
    }

    public boolean editarVehiculo(ModeloVehiculo vehiculo) {
        try {
            MongoCollection<Document> collection = getCollection();

            Document filtro = new Document("codigo", vehiculo.getCodigo());

            Document update = new Document("$set",
                    new Document("marca", vehiculo.getMarca())
                            .append("modelo", vehiculo.getModelo())
                            .append("precio", vehiculo.getPrecio())
            );

            collection.updateOne(filtro, update);

            return true;

        } catch (Exception e) {
            System.out.println("Error editar: " + e.getMessage());
            return false;
        }
    }

    public ModeloVehiculo buscarPorCodigo(String codigoBuscando) {
        try {
            MongoCollection<Document> collection = getCollection();

            Document filtro = new Document("codigo", codigoBuscando);

            Document resultado = collection.find(filtro).first();

            if (resultado != null) {

                ModeloVehiculo vehiculo = new ModeloVehiculo();

                vehiculo.setCodigo(resultado.getString("codigo"));
                vehiculo.setMarca(resultado.getString("marca"));
                vehiculo.setModelo(resultado.getString("modelo"));

                Object precioObj = resultado.get("precio");

                if (precioObj instanceof Number) {
                    vehiculo.setPrecio(((Number) precioObj).doubleValue());
                }

                return vehiculo;
            }

        } catch (Exception e) {
            System.out.println("Error buscar: " + e.getMessage());
        }

        return null;
    }
}