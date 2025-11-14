 
package persistencia;

import dto.DtoArticuloRegistro;
import java.io.InputStream;
import java.util.List;
import modelo.*;



public interface DaoArticulo {
     
    public boolean registrar(DtoArticuloRegistro libro, InputStream inputStream) throws Exception;
    public boolean actualizar(Articulo libro) throws Exception;
    public boolean eliminar(Articulo libro) throws Exception;
    public List<Articulo> listar() throws Exception; 
    public boolean cambiarEstadoF(int id) throws Exception;
    public boolean cambiarEstadoT(int id) throws Exception;
}
