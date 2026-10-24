package service;

import model.Productos;
import repository.AlmacenRepository;

import java.util.List;

// Esta clase es mi puente junta lo que me bajo de internet con mi repositorio para guardarlo en la base de datos
public class AlmacenService {

    private final AlmacenRepository repository;
    private final DummyJsonService dummyJsonService;

    public AlmacenService() {
        this.repository = new AlmacenRepository();
        this.dummyJsonService = new DummyJsonService();
    }

    // Aquí hago toda la carga inicial de golpe para tener la base de datos lista
    public void cargarDatosIniciales() {
        //Me bajo los productos de internet y los guardo en la tabla productos
        List<Productos> productos = dummyJsonService.obtenerProductos();
        if (!productos.isEmpty()) {
            repository.guardarProductos(productos);
        }

        //Meto los empleados y pedidos de prueba con Statement
        repository.insertarEmpleadosYPedidos();

        //Meto en favoritos los que cuesten más de 1000 €
        repository.insertarFavoritosMayores1000();
    }

    // Consulta 1: Devuelvo todos los productos
    public List<Productos> obtenerTodos() {
        return repository.obtenerTodosLosProductos();
    }

    // Consulta 2: Devuelvo los favoritos cruzados con sus datos
    public List<Productos> obtenerFavoritosConDatos() {
        return repository.obtenerFavoritosConDatos();
    }

    // Consulta 3: Devuelvo los productos de menos de 600 €
    public List<Productos> obtenerMenoresA600() {
        return repository.obtenerProductosMenoresA600();
    }
}
