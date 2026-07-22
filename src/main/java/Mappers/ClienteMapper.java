/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.ClienteDTO;
import Modelo.Cliente;

/**
 *
 * @author USER
 */
public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setDni(cliente.getDni());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setOcupacion(cliente.getOcupacion());
        dto.setCodigodecliente(cliente.getCodigocliente());
        dto.setCorreo(cliente.getCorreo());
        dto.setEstado(cliente.isEstado());
        dto.setIngresos(cliente.getIngresos());
        return dto;
    }
    public static Cliente toEntity(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setDni(dto.getDni());
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setOcupacion(dto.getOcupacion());
        cliente.setCodigocliente(dto.getCodigodecliente());
        cliente.setCorreo(dto.getCorreo());
        cliente.setEstado(dto.isEstado());
        cliente.setIngresos(dto.getIngresos());
        return cliente;
    }
}
