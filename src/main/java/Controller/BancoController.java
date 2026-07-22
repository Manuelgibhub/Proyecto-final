/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DTO.BancoDTO;
import Mappers.BancoMapper;
import Service.IBancoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import Modelo.Banco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class BancoController {
    
    @Autowired
    private IBancoService bancoService;
    public BancoDTO registrarBanco(BancoDTO dto){
        Banco banco = BancoMapper.toEntity(dto);
        Banco guardado = bancoService.guardar(banco);
        return BancoMapper.toDTO(guardado);
    }
    public List<BancoDTO> obtenerBancos(){
        return bancoService.listarTodos().stream()
                .map(BancoMapper::toDTO).collect(Collectors.toList());
    }
    public Optional<BancoDTO> buscarPorId(long id){
        return bancoService.buscarPorId(id).map(BancoMapper::toDTO);
    }
    public void eliminarBanco(long id){
        bancoService.eliminar(id);
    }

}
