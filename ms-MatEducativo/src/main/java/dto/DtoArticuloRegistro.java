/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package dto;

/**
 *
 * @author ACER-A315-59
 */
public record DtoArticuloRegistro(int id_articulo,int id_usuario,String nombre, String descripcion,String categoria,int volumen, String año_publicacion,int cantPaginas, String tipo) {

}
