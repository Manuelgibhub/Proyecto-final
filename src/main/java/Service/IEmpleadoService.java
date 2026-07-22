/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Empleado;

/**
 *
 * @author USER
 */
public interface IEmpleadoService {
    Empleado guardar(Empleado empleado);
    List<Empleado> listartodos();
    Optional<Empleado> buscarPorId(long id);
    Optional<Empleado> buscarPorCodigodeEmpleado(int codigodeempleado);
    void eliminar(long id);
}
