/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private long id;
    private int dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String ocupacion;
    private int codigodecliente;
    private String correo;
    private boolean estado;
    private double ingresos;
}
