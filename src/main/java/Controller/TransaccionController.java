package Controller;

import DTO.TransaccionDTO;
import Mappers.TransaccionMapper;
import Modelo.TipodeTransaccion;
import Service.ICuentaService;
import Service.ITransaccionService;
import java.util.List;
import java.util.stream.Collectors;
import Modelo.Cuenta;
import Modelo.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    @Autowired
    private ICuentaService cuentaService;

    public TransaccionDTO registrarTransaccion(TransaccionDTO dto) {
        Cuenta cuenta = cuentaService.buscarPorID(dto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id: " + dto.getCuentaId()));

        if (dto.getTipodeTransaccion() == TipodeTransaccion.RETIRO) {
            if (cuenta.getSaldo() < dto.getMonto()) {
                throw new RuntimeException("Saldo insuficiente. Saldo actual: " + cuenta.getSaldo());
            }
            cuenta.setSaldo(cuenta.getSaldo() - dto.getMonto());
        } else {
            cuenta.setSaldo(cuenta.getSaldo() + dto.getMonto());
        }

   
        cuentaService.guardar(cuenta);

      
        Transaccion transaccion = TransaccionMapper.toEntity(dto, cuenta);
        Transaccion guardada = transaccionService.guardar(transaccion);
        return TransaccionMapper.toDTO(guardada);
    }

    public List<TransaccionDTO> obtenerTransacciones() {
        return transaccionService.ListarTodos().stream()
                .map(TransaccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void eliminarTransaccion(long id) {
        transaccionService.eliminar(id);
    }
}
