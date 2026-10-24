import model.Productos;
import service.AlmacenService;

import java.util.List;

// Mi clase principal para arrancar el programa y enseñar todo
@SuppressWarnings("all")
public class Main {

    private static final AlmacenService service = new AlmacenService();

    public static void main(String[] args) {
        System.out.println("                  DEMO NORTHWIND                            ");

        // Primero hago la carga de datos (DummyJSON, empleados, pedidos y favoritos)
        System.out.println("\n[SISTEMA] Cargando datos en MySQL (DummyJSON, Empleados, Pedidos, Favoritos)");
        service.cargarDatosIniciales();
        System.out.println("[SISTEMA] Carga completada con éxito.\n");

        //Muestro por consola todos los productos
        imprimirTabla(service.obtenerTodos(), "1. TODOS LOS PRODUCTOS (QUERY - STATEMENT)");

        //Muestro por consola los favoritos con sus datos (cruzando las tablas con INNER JOIN)
        imprimirTabla(service.obtenerFavoritosConDatos(), "2. PRODUCTOS FAVORITOS CON SUS DATOS (QUERY - STATEMENT [INNER JOIN])");

        //Muestro por consola los productos baratitos que cuestan menos de 600 €
        imprimirTabla(service.obtenerMenoresA600(), "3. PRODUCTOS CON PRECIO INFERIOR A 600 EUR");

        System.out.println("                  DEMOSTRACIÓN FINALIZADA CON ÉXITO                                       ");
    }

    // Este metodo pinta las tablas bien ordenaditas en la consola y que no se vea todo apretado
    private static void imprimirTabla(List<Productos> lista, String titulo) {
        System.out.println("\n==========================================================================================");
        System.out.println("[TABLA] " + titulo + " - TOTAL: " + lista.size());
        System.out.println("==========================================================================================");
        System.out.printf("| %-5s | %-45s | %-12s | %-8s |\n", "ID", "NOMBRE DEL PRODUCTO", "PRECIO (EUR)", "STOCK");
        System.out.println("------------------------------------------------------------------------------------------");

        if (lista.isEmpty()) {
            System.out.println("| (No hay registros para mostrar)                                                        |");
        } else {
            for (Productos p : lista) {
                // Si el nombre es larguísimo le pongo puntos suspensivos para que no me descuadre la tabla
                String nombre = p.getNombre().length() > 45 ? p.getNombre().substring(0, 42) + "..." : p.getNombre();
                System.out.printf("| %-5d | %-45s | %10.2f EUR | %8d |\n",
                        p.getId(), nombre, p.getPrecio(), p.getCantidad());
            }
        }
        System.out.println("==========================================================================================");
    }
}
