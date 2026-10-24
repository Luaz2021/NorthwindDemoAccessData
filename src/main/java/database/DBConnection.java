package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Aquí me guardo una sola conexión a MySQL para no estar abriendo y cerrando a cada rato
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    // Los datos de mi base de datos local en Laragon / MariaDB
    private static final String URL = "jdbc:mysql://localhost:3306/almacen";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Constructor privado para que nadie me cree objetos con new desde fuera
    private DBConnection() {
        conectar();
    }

    // Aquí me conecto directamente a la base de datos
    private void conectar() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión Exitosa a la base de datos");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    // Si todavía no me he conectado, creo la conexión; si ya la tengo, uso la misma
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    // Este es el metodo que llamo cuando necesito hacer mis consultas
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                conectar();
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al reconectar: " + e.getMessage());
        }
        return connection;
    }
}
