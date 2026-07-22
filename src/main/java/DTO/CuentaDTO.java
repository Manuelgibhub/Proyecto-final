package DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Modelo.TipodeCuenta;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private long id;
    private int codigodecuenta;
    private TipodeCuenta tipodeCuenta;
    private double saldo;
    private LocalDate fechadecreacion;
    private String estado;
    private long clienteId;
}
