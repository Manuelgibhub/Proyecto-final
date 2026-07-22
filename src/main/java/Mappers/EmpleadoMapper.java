package Mappers;

import DTO.EmpleadoDTO;
import Modelo.Empleado;

public class EmpleadoMapper {

    public static EmpleadoDTO toDTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setDni(empleado.getDni());
        dto.setNombre(empleado.getNombre());
        dto.setApellido(empleado.getApellido());
        dto.setTelefono(empleado.getTelefono());
        dto.setDireccion(empleado.getDireccion());
        dto.setOcupacion(empleado.getOcupacion());
        dto.setCodigodeempleado(empleado.getCodigodeempleado());
        dto.setCargo(empleado.getCargo());
        dto.setSalario(empleado.getSalario());
        dto.setBancoId(empleado.getBanco() != null ? empleado.getBanco().getId() : null);
        return dto;
    }

    public static Empleado toEntity(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setId(dto.getId());
        empleado.setDni(dto.getDni());
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setTelefono(dto.getTelefono());
        empleado.setDireccion(dto.getDireccion());
        empleado.setOcupacion(dto.getOcupacion());
        empleado.setCodigodeempleado(dto.getCodigodeempleado());
        empleado.setCargo(dto.getCargo());
        empleado.setSalario(dto.getSalario());
        // banco se asigna desde el controller si se provee bancoId
        return empleado;
    }
}
