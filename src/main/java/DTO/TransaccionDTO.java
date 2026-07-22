package DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Modelo.TipodeTransaccion;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDTO {
    private long id;
    private int codigodeTransaccion;
    private LocalDate fechadeTransaccion;
    private double monto;
    private TipodeTransaccion tipodeTransaccion;
    private long cuentaId;
}
