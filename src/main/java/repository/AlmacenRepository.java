package repository;

import database.DBConnection;
import database.SQLQueries;
import model.Productos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// En esta clase tengo todos los métodos que tocan la base de datos
@SuppressWarnings({"SqlNoDataSourceInspection", "SpellCheckingInspection"})
public class AlmacenRepository {

    //Aquí guardo todos los productos de golpe en la tabla usando PreparedStatement por lotes
    public void guardarProductos(List<Productos> lista) {
        try (PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(SQLQueries.INSERT_PRODUCTO)) {

            for (Productos p : lista) {
                ps.setInt(1, p.getId());
                ps.setString(2, p.getNombre());
                ps.setString(3, p.getDescripcion());
                ps.setInt(4, p.getCantidad());
                ps.setDouble(5, p.getPrecio());
                ps.addBatch();
            }

            // Mando todos los inserts de golpe
            ps.executeBatch();

        } catch (SQLException e) {
            System.err.println("[ERROR] Error guardando productos en lote: " + e.getMessage());
        }
    }

    //Aquí meto los empleados y pedidos a mano usando Statement directo como me pide el enunciado
    public void insertarEmpleadosYPedidos() {
        try (Statement stmt = DBConnection.getInstance().getConnection().createStatement()) {

            // Meto los dos empleados
            stmt.executeUpdate(SQLQueries.INSERT_EMPLEADO_1);
            stmt.executeUpdate(SQLQueries.INSERT_EMPLEADO_2);

            // Meto los pedidos vinculándolos a productos y empleados que existen de verdad
            stmt.executeUpdate(SQLQueries.INSERT_PEDIDO_1);
            stmt.executeUpdate(SQLQueries.INSERT_PEDIDO_2);

        } catch (SQLException e) {
            System.err.println("[ERROR] Error insertando serie de empleados/pedidos: " + e.getMessage());
        }
    }

    //Con esto meto a la tabla favoritos los que pasen de 1000 € usando Statement
    public void insertarFavoritosMayores1000() {
        try (Statement stmt = DBConnection.getInstance().getConnection().createStatement()) {

            stmt.executeUpdate(SQLQueries.INSERT_FAVORITOS_MAYORES_1000);

        } catch (SQLException e) {
            System.err.println("[ERROR] Error al insertar productos favoritos: " + e.getMessage());
        }
    }

    //Consulta 1 me traigo todos los productos de la tabla para mostrarlos por pantalla
    public List<Productos> obtenerTodosLosProductos() {
        List<Productos> lista = new ArrayList<>();
        try (Statement stmt = DBConnection.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_TODOS)) {

            while (rs.next()) {
                lista.add(new Productos(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        "",
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                ));
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] Error consultando todos los productos: " + e.getMessage());
        }
        return lista;
    }

    //Consulta 2 me traigo los favoritos
    public List<Productos> obtenerFavoritosConDatos() {
        List<Productos> lista = new ArrayList<>();
        try (Statement stmt = DBConnection.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_FAVORITOS_CON_DATOS)) {

            while (rs.next()) {
                lista.add(new Productos(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        "",
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                ));
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] Error consultando favoritos con JOIN: " + e.getMessage());
        }
        return lista;
    }

    //Consulta 3 me traigo solo los productos que cuesten menos de 600 €
    public List<Productos> obtenerProductosMenoresA600() {
        List<Productos> lista = new ArrayList<>();
        try (Statement stmt = DBConnection.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_MENORES_600)) {

            while (rs.next()) {
                lista.add(new Productos(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        "",
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                ));
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] Error consultando productos menores a 600 €: " + e.getMessage());
        }
        return lista;
    }
}
