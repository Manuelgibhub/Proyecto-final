/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controller;

import DTO.ClienteDTO;
import Mappers.ClienteMapper;
import Service.IClienteService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import Modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteController {
    @Autowired
    private IClienteService clienteService;
    public ClienteDTO regitrarCliente(ClienteDTO dto){
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente guardado = clienteService.guardar(cliente);
        return ClienteMapper.toDTO(guardado);
    }
    public List<ClienteDTO> obtenerClientes(){
        return clienteService.listarTodos().stream()
                .map(ClienteMapper::toDTO).collect(Collectors.toList());
    }
    public Optional<ClienteDTO> buscarPorId(long id){
        return clienteService.buscarPorId(id)
               .map(ClienteMapper :: toDTO);
    }
    public Optional<ClienteDTO> buscarPorCodigo(int codigocliente){
        return clienteService.buscarPorCodigo(codigocliente).map(ClienteMapper::toDTO);
    }
    public void eliminarCliente(long id){
        clienteService.eliminar(id);
    }
}
