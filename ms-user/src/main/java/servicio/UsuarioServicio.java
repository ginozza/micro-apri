/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dto.DtoUsuarioLogin;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import persistencia.DaoUsuario;
import persistencia.DaoUsuarioImpPostgres;

/**
 *
 * @author ACER-A315-59
 */
public class UsuarioServicio {
    
    DaoUsuario daoUser;
    
    public UsuarioServicio(){
        daoUser = new DaoUsuarioImpPostgres();
    }
    
    public List<DtoUsuarioLogin> listUser() throws Exception{
                    System.out.println("PASO LA sERVICIO");

        List<DtoUsuarioLogin> listaDto = new ArrayList<>();
        if(daoUser.listar()!=null){
            
            List<Usuario> listU= daoUser.listar();
            if(listU!=null){
                for (Usuario usuario : listU) {
                    listaDto.add(new DtoUsuarioLogin(usuario.getId_persona(), usuario.getCorreo(), usuario.getPrimer_nombre(), 
                                    usuario.getPrimer_apellido(), usuario.getTipo(),usuario.isEstado()));
                }
                return listaDto;
            }
        }
        return null;
    }
    
    
}
