package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Mi clase para los pedidos vinculados a productos y empleados
// Con Lombok me ahorro escribir a mano constructores, getters y setters
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Pedidos {
    private int id;
    private int idProducto;
    private int idEmpleado;
}
