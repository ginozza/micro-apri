
package dto;


public record DtoUsuarioLogin(int id_persona,String correo,String primerNombre,String primerApellido, String tipo,
        boolean estado) implements DtoPersonaLogin  {
    
}
