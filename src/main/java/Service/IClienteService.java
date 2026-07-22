/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Cliente;

/**
 *
 * @author USER
 */
public interface IClienteService {
    Cliente guardar(Cliente cliente);
    List<Cliente> listarTodos();
    Optional<Cliente> buscarPorId(long id);
    Optional<Cliente> buscarPorCodigo(int codigocliente);
    void eliminar(long id);
}
