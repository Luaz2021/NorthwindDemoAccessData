package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Mi clase para guardar los datos de cada producto de la tabla
// Le meto Lombok porque me daba pereza escribir toda la estructura a mano, hace exactamente lo mismo: crea constructores, getters, setters, etc.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Productos {
    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
}
