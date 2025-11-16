
package servicio;

import dto.DtoLibroRegistro;
import dto.DtoMatEducativo;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Libro;
import modelo.MaterialEducativo;
import persistencia.ApriException;
import persistencia.DaoLibro;
import persistencia.DaoLibroImp;



public class LibroServicio {
    
    DaoLibro daoLib;
    
    
    public LibroServicio(){
        daoLib = new DaoLibroImp();
    }
    
    
        public List<DtoMatEducativo> listarMateriales(){
            List<DtoMatEducativo> listaF = new ArrayList<>();
                    
            try {
                List<MaterialEducativo> listaM = daoLib.listarM();
                if(listaM!=null){
                    for (MaterialEducativo m : listaM) {
                        listaF.add(new DtoMatEducativo(m.getId_materialEducativo(),m.getNombre(), m.getCategoria(),
                               String.valueOf( m.getAño_publicacion()), m.getDescripcion(),m.getTipo()));

                    }
                    return listaF;
                }
            } catch (Exception ex) {
                System.out.println("Error: "+ex.getMessage());
            }
                return null;
        }
    
    
    public boolean subirLibro(DtoLibroRegistro librito, InputStream inputStream) throws Exception {
    
        if(librito != null && librito.id_usuario()>0 && librito.cantPaginas()>0 && inputStream != null){
            System.out.println("Paso los filtros del libro servicio");
                    try {
                        Libro libro = new Libro(librito.edicion(), librito.editorial(), librito.cantPaginas(),1, librito.categoria(), librito.nombre(),LocalDate.parse(librito.año_publicacion()),librito.tipo(),librito.descripcion(), true);
                        return daoLib.registrar(libro,inputStream,librito.id_usuario());
                       
                    } catch (Exception ex) {
                        System.err.println("Error en DAO: " + ex.getMessage());
                        throw new ApriException("Fallo el uso del DAOLIBRO: " + ex.getMessage());
                    }
        } else {
            System.out.println("FALLO: Algún parámetro es null");
        }
        return false;
        }

        public List<DtoMatEducativo> buscarMaterialPorUsuario(int idPersona) throws Exception {


            if(idPersona>0){
                return daoLib.buscarListUser(idPersona);

            }


            return null;
    }
}
