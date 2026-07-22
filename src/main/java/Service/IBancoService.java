/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Banco;

/**
 *
 * @author USER
 */
public interface IBancoService {
    Banco guardar(Banco banco);
    List<Banco> listarTodos();
    Optional<Banco> buscarPorId(long id);
    void eliminar(long id);
}
