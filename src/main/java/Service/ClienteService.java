/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.ClienteRepository;
import Service.IClienteService;

/**
 *
 * @author USER
 */
@Service
public class ClienteService implements IClienteService{
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> buscarPorCodigo(int codigocliente) {
        return clienteRepository.findByCodigocliente(codigocliente);
    }

    @Override
    public void eliminar(long id) {
        clienteRepository.deleteById(id);
    }
    
}
