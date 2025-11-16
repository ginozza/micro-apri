
package dto;


public record DtoUsuarioLogin(int id_persona,
        String correo,String primerNombre,
        String primerApellido, String tipo,
        boolean estado, String institucion, String fecha_nacimiento) implements DtoPersonaLogin  {
    
}
