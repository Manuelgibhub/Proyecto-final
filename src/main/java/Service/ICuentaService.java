/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Cuenta;

/**
 *
 * @author USER
 */
public interface ICuentaService {
    Cuenta guardar(Cuenta cuenta);
    List<Cuenta> listarTodos();
    Optional<Cuenta> buscarPorID(long id);
    List<Cuenta> buscarPorClienteId(long clienteId);
    void eliminar(long id);
}
