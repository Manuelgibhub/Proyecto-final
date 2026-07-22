/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Service.ITransaccionService;
import java.util.List;
import java.util.Optional;
import Modelo.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.TransaccionRepository;

/**
 *
 * @author USER
 */
@Service
public class TransaccionService implements ITransaccionService{
    
    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public Transaccion guardar(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Override
    public List<Transaccion> ListarTodos() {
        return transaccionRepository.findAll();
    }

    @Override
    public Optional<Transaccion> buscarPorId(long id) {
        return transaccionRepository.findById(id);
    }

    @Override
    public void eliminar(long id) {
        transaccionRepository.deleteById(id);
    }
}
