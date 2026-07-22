/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.CuentaDTO;
import Modelo.Cliente;
import Modelo.Cuenta;

/**
 *
 * @author USER
 */
public class CuentaMapper {
    public static CuentaDTO toDTO(Cuenta cuenta){
        CuentaDTO dto = new CuentaDTO();
        dto.setId(cuenta.getId());
        dto.setCodigodecuenta(cuenta.getCodigocuenta());
        dto.setTipodeCuenta(cuenta.getTipodecuenta());
        dto.setSaldo(cuenta.getSaldo());
        dto.setFechadecreacion(cuenta.getFechadecreacion());
        dto.setEstado(cuenta.getEstado());
        dto.setClienteId(cuenta.getCliente()!= null ? cuenta.getCliente().getId() : 0);
        return dto;
    }
    public static Cuenta toEntity(CuentaDTO dto, Cliente cliente){
        Cuenta cuenta = new Cuenta();
        cuenta.setId(dto.getId());
        cuenta.setCodigocuenta(dto.getCodigodecuenta());
        cuenta.setTipodecuenta(dto.getTipodeCuenta());
        cuenta.setSaldo(dto.getSaldo());
        cuenta.setFechadecreacion(dto.getFechadecreacion());
        cuenta.setEstado(dto.getEstado());
        cuenta.setCliente(cliente);
        return cuenta;
    }
}
