package Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Empleado extends Persona {

    private int codigodeempleado;
    private String cargo;
    private double salario;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;
}
