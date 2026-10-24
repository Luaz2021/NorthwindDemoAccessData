package service;

import model.Productos;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// Con esta clase me conecto a internet y me bajo los productos que nos pide el profe
@SuppressWarnings("all")
public class DummyJsonService {

    // El enlace oficial de DummyJSON
    private static final String URL_API = "https://dummyjson.com/products";

    public List<Productos> obtenerProductos() {
        // Me creo una lista vacía para ir guardando los productos que me baje
        List<Productos> lista = new ArrayList<>();
        try {
            // Me conecto a la URL con una petición GET de toda la vida
            URL url = URI.create(URL_API).toURL();
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            // Leo todo el chorro de texto que me escupe la web línea por línea
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    respuesta.append(linea);
                }

                // Convierto ese texto en un JSON y saco el array de productos
                JSONObject jsonObject = new JSONObject(respuesta.toString());
                JSONArray array = jsonObject.getJSONArray("products");

                // Me recorro el array con un for clásico de toda la vida
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);

                    // Saco dato por dato de cada producto
                    int id = item.getInt("id");
                    String nombre = item.getString("title");
                    String descripcion = item.getString("description");
                    int cantidad = item.getInt("stock");
                    double precio = item.getDouble("price");

                    // Lo creo y lo meto directo a mi lista
                    lista.add(new Productos(id, nombre, descripcion, cantidad, precio));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al consumir API DummyJSON: " + e.getMessage());
        }
        // Devuelvo mi lista con todos los productos listos
        return lista;
    }
}
