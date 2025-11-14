
package servicio;

import dto.DtoArticuloRegistro;
import java.io.InputStream;
import persistencia.ApriException;
import persistencia.DaoArticulo;
import persistencia.DaoArticuloImpPostgres;



public class ArticuloServicio {
    
    DaoArticulo daoArticulo;
    
    
    public ArticuloServicio(){
        daoArticulo = new DaoArticuloImpPostgres();
    }
    
    
    
    
    
    public boolean subirArticulo(DtoArticuloRegistro articulo, InputStream inputStream) throws Exception {
    
        if(articulo != null && articulo.id_usuario()>0 && articulo.cantPaginas()>0 && inputStream != null){
            System.out.println("Paso los filtros del articulo servicio");
                    try {
                        //Libro libro = new Libro(librito.edicion(), librito.editorial(), librito.cantPaginas(),1, librito.categoria(), librito.nombre(),LocalDate.parse(librito.año_publicacion()),librito.tipo(),librito.descripcion(), true, null);
                        return daoArticulo.registrar(articulo,inputStream);
                    } catch (Exception ex) {
                        System.err.println("Error en DAO: " + ex.getMessage());
                        throw new ApriException("Fallo el uso del DAOARTICULO: " + ex.getMessage());
                    }
        } else {
            System.out.println("FALLO: Algún parámetro es null");
        }
        return false;
        }

       
    
}
