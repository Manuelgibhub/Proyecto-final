/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.CuentaRepository;
import Service.ICuentaService;

/**
 *
 * @author USER
 */
@Service
public class CuentaService implements ICuentaService {
    
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta guardar(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public List<Cuenta> listarTodos() {
        return cuentaRepository.findAll();
    }

    @Override
    public Optional<Cuenta> buscarPorID(long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public List<Cuenta> buscarPorClienteId(long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }

    @Override
    public void eliminar(long id) {
        cuentaRepository.deleteById(id);
    }
    
}
