/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.EmpleadoRepository;
import Service.IEmpleadoService;

/**
 *
 * @author USER
 */
@Service
public class EmpleadoService implements IEmpleadoService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> listartodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> buscarPorId(long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Optional<Empleado> buscarPorCodigodeEmpleado(int codigodeempleado) {
        return empleadoRepository.findBycodigodeempleado(codigodeempleado);
    }

    @Override
    public void eliminar(long id) {
        empleadoRepository.deleteById(id);
    }
    
}
