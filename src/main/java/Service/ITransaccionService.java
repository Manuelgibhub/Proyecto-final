/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Transaccion;

/**
 *
 * @author USER
 */
public interface ITransaccionService {
    Transaccion guardar(Transaccion transaccion);
    List<Transaccion> ListarTodos();
    Optional<Transaccion> buscarPorId(long id);
    void eliminar(long id);
}
