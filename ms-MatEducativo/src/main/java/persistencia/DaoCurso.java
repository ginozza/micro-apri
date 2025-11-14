 
package persistencia;

import dto.DtoCursoRegistro;
import java.util.List;
import modelo.*;



public interface DaoCurso {
     
    public int registrar(DtoCursoRegistro libro) throws Exception;
    public boolean actualizar(Curso libro) throws Exception;
    public boolean eliminar(Curso libro) throws Exception;
    public List<Curso> listar() throws Exception; 
    public boolean cambiarEstadoF(int id) throws Exception;
    public boolean cambiarEstadoT(int id) throws Exception;
}
