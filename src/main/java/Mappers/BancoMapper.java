/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.BancoDTO;
import Modelo.Banco;

/**
 *
 * @author USER
 */
public class BancoMapper {
    public static BancoDTO toDTO(Banco banco){
        BancoDTO dto = new BancoDTO();
        dto.setId(banco.getId());
        dto.setNombre(banco.getNombre());
        return dto;
    }
    public static Banco toEntity(BancoDTO dto){
        Banco banco = new Banco();
        banco.setId(dto.getId());
        banco.setNombre(dto.getNombre());
        return banco;
    }
}
