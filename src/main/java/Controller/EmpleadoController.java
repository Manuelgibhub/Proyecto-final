package Controller;

import DTO.EmpleadoDTO;
import Mappers.EmpleadoMapper;
import Service.IBancoService;
import Service.IEmpleadoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import Modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IBancoService bancoService;

    public EmpleadoDTO registrarEmpleado(EmpleadoDTO dto) {
        Empleado empleado = EmpleadoMapper.toEntity(dto);
        if (dto.getBancoId() != null) {
            bancoService.buscarPorId((long) dto.getBancoId())
                    .ifPresent(empleado::setBanco);
        }
        Empleado guardado = empleadoService.guardar(empleado);
        return EmpleadoMapper.toDTO(guardado);
    }

    public List<EmpleadoDTO> obtenerEmpleados() {
        return empleadoService.listartodos().stream()
                .map(EmpleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmpleadoDTO> buscarPorCodigodeEmpleado(int codigodeempleado) {
        return empleadoService.buscarPorCodigodeEmpleado(codigodeempleado)
                .map(EmpleadoMapper::toDTO);
    }

    public void eliminarEmpleado(long id) {
        empleadoService.eliminar(id);
    }
}
