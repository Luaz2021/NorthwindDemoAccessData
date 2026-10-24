package database;

// Aquí me dejo escritas todas las consultas SQL bien ordenaditas para no tenerlas
// regadas por todo el codigo
// @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve", "SpellCheckingInspection"})
public class SQLQueries {

    private SQLQueries() {
        // Constructor vacío para no instanciar esta clase
    }

    //Con esto meto los productos que me bajo de internet a mi tabla productos
    // language=none
    public static final String INSERT_PRODUCTO =
            "INSERT INTO productos (id, nombre, descripcion, cantidad, precio) " +
            "VALUES (?, ?, ?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE precio = VALUES(precio)";

    //Aquí me invento un par de empleados y pedidos a mano para cumplir lo que pide el enunciado
    // language=none
    public static final String INSERT_EMPLEADO_1 =
            "INSERT INTO empleados (nombre, puesto) VALUES ('Alexander Martinez', 'Director General')";

    // language=none
    public static final String INSERT_EMPLEADO_2 =
            "INSERT INTO empleados (nombre, puesto) VALUES ('Roberto Garcia', 'Jefe de Almacén')";

    // language=none
    public static final String INSERT_PEDIDO_1 =
            "INSERT INTO pedidos (id_producto, id_empleado) VALUES (1, 1)";

    // language=none
    public static final String INSERT_PEDIDO_2 =
            "INSERT INTO pedidos (id_producto, id_empleado) VALUES (2, 2)";

    // Con esto meto directo a la tabla de favoritos los productos caros que pasen de 1000 €
    // language=none
    public static final String INSERT_FAVORITOS_MAYORES_1000 =
            "INSERT IGNORE INTO productos_fav (id_producto) " +
            "SELECT id FROM productos WHERE precio > 1000";

    //Primera consulta traerme todos los productos de la tabla
    // language=none
    public static final String SELECT_TODOS =
            "SELECT id, nombre, precio, cantidad FROM productos";

    //Segunda consulta saco los favoritos cruzando las dos tablas
    // language=none
    public static final String SELECT_FAVORITOS_CON_DATOS =
            "SELECT p.id, p.nombre, p.precio, p.cantidad " +
            "FROM productos p " +
            "INNER JOIN productos_fav f ON p.id = f.id_producto";

    //Tercera consulta saco los productos baratitos que cuesten menos de 600 €
    // language=none
    public static final String SELECT_MENORES_600 =
            "SELECT id, nombre, precio, cantidad FROM productos WHERE precio < 600";
}
