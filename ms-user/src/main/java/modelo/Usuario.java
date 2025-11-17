
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona{
    

    public Usuario() {
    }

    public Usuario(int id_persona, String primer_nombre, String primer_apellido, LocalDate fecha_nacimiento, LocalDate fecha_registro, String correo, String institucion, String contraseña, boolean estado, String tipo) {
        super(id_persona, primer_nombre, primer_apellido, fecha_nacimiento, fecha_registro, correo, institucion, tipo, contraseña, estado);

    }

    @Override
    public String toString() {
        return "Usuario{" +super.toString()+'}';
    }

    
    
    

    
}
