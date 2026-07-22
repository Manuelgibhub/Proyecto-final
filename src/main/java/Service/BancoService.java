/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.List;
import java.util.Optional;
import Modelo.Banco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.BancoRepository;
import Service.IBancoService;

/**
 *
 * @author USER
 */
@Service
public class BancoService implements IBancoService {
    
    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public Banco guardar(Banco banco) {
        return bancoRepository.save(banco);
    }

    @Override
    public List<Banco> listarTodos() {
        return bancoRepository.findAll();
    }

    @Override
    public Optional<Banco> buscarPorId(long id) {
        return bancoRepository.findById(id);
    }

    @Override
    public void eliminar(long id) {
        bancoRepository.deleteById(id);
    }
    
}
