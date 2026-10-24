package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Mi clase para la tabla de favoritos
// También con Lombok porque me daba pereza meter los getters, setters y constructores a mano
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class ProductosFav {
    private int idProducto;
}
