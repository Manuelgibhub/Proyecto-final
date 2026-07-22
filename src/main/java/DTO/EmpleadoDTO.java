package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {
    private long id;
    private int dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String ocupacion;
    private int codigodeempleado;
    private String cargo;
    private double salario;
    private Long bancoId;
}
