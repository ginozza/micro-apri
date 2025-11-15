 
package persistencia;

import dto.DtoModuloRegistrar;
import java.util.List;
import modelo.*;



public interface DaoModulo {
     
    public boolean registrar(Modulo modulo, int id_curso) throws Exception;
    public boolean actualizar(Modulo modulo) throws Exception;
    public boolean eliminar(Modulo modulo) throws Exception;
    public List<Modulo> listar() throws Exception; 
    public List<Modulo> listarPorId(int id_curso) throws Exception; 
    public boolean cambiarEstadoF(int id) throws Exception;
    public boolean cambiarEstadoT(int id) throws Exception;
}
