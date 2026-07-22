/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.TransaccionDTO;
import Modelo.Cuenta;
import Modelo.Transaccion;

/**
 *
 * @author USER
 */
public class TransaccionMapper {
    public static TransaccionDTO toDTO(Transaccion transaccion){
        TransaccionDTO dto = new TransaccionDTO();
        dto.setId(transaccion.getId());
        dto.setCodigodeTransaccion(transaccion.getCodigodetransaccion());
        dto.setFechadeTransaccion(transaccion.getFechadetransaccion());
        dto.setMonto(transaccion.getMonto());
        dto.setTipodeTransaccion(transaccion.getTipodeTransaccion());
        dto.setCuentaId(transaccion.getCuenta() != null ? transaccion.getCuenta().getId() : 0);
        return dto;
    }
    public static Transaccion toEntity(TransaccionDTO dto, Cuenta cuenta){
        Transaccion transaccion = new Transaccion();
        transaccion.setId(dto.getId());
        transaccion.setCodigodetransaccion(dto.getCodigodeTransaccion());
        transaccion.setFechadetransaccion(dto.getFechadeTransaccion());
        transaccion.setMonto(dto.getMonto());
        transaccion.setTipodeTransaccion(dto.getTipodeTransaccion());
        transaccion.setCuenta(cuenta);
        return transaccion;
    }
}
