package Modelo;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente extends Persona {

    private int codigocliente;
    private String correo;
    private boolean estado;
    private double ingresos;
}
