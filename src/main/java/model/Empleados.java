package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Mi clase para los empleados de prueba
// Uso Lombok para no picar código de más, me genera solo los constructores y getters/setters
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Empleados {
    private int id;
    private String nombre;
    private String puesto;
}
