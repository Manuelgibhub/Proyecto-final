/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DTO.CuentaDTO;
import Mappers.CuentaMapper;
import Service.IClienteService;
import Service.ICuentaService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import Modelo.Cliente;
import Modelo.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class CuentaController {
    
    @Autowired
    private ICuentaService cuentaService;
    @Autowired
    private IClienteService clienteService;
    public CuentaDTO abrirCuenta(CuentaDTO dto){
        Cliente cliente = clienteService.buscarPorId(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + dto.getClienteId()));
        Cuenta cuenta = CuentaMapper.toEntity(dto, cliente);
        Cuenta guardada = cuentaService.guardar(cuenta);
        return CuentaMapper.toDTO(guardada);
    }
    public List<CuentaDTO> obtenerCuentas(){
        return cuentaService.listarTodos().stream()
                .map(CuentaMapper::toDTO).collect(Collectors.toList());
    }
    public Optional<CuentaDTO> buscarPorId(long id){
            return cuentaService.buscarPorID(id).map(CuentaMapper::toDTO);
        }
    public List<CuentaDTO> buscarCuentasPorCliente(long clienteId) {
        return cuentaService.buscarPorClienteId(clienteId).stream()
                .map(CuentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void eliminarCuenta(long id){
        cuentaService.eliminar(id);
    }
}
