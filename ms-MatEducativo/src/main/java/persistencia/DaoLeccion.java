 
package persistencia;

import dto.DtoLeccionLista;
import java.util.List;
import modelo.*;



public interface DaoLeccion {
     
    public boolean registrar(DtoLeccionLista leccionDto) throws Exception;
    public boolean actualizar(Leccion leccion) throws Exception;
    public boolean eliminar(Leccion leccion) throws Exception;
    public List<Leccion> listar() throws Exception; 
    public List<Leccion> listarPorId(int id_modulo) throws Exception; 
    public boolean cambiarEstadoF(int id) throws Exception;
    public boolean cambiarEstadoT(int id) throws Exception;
}
