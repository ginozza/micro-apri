
package servicio;

import dto.DtoLeccionLista;

import java.util.ArrayList;
import java.util.List;
import modelo.Leccion;
import persistencia.ApriException;
import persistencia.DaoLeccion;
import persistencia.DaoLeccionImpPostgres;



public class LeccionServicio {
    
    DaoLeccion daoLeccion;
    
    
    public LeccionServicio(){
        daoLeccion = new DaoLeccionImpPostgres();
    }
    
    
    
    
    
    public boolean subirLeccion(DtoLeccionLista leccionDto) throws Exception {
   
        if(leccionDto != null ){
            System.out.println("Paso los filtros del modulo servicio");
                    try {
                        Leccion lec = new Leccion(1,leccionDto.nombre(), leccionDto.url_video(), leccionDto.descripcion());
                        return daoLeccion.registrar(lec, leccionDto.id_modulo());
                    } catch (Exception ex) {
                        System.err.println("Error en DAO: " + ex.getMessage());
                        throw new ApriException("Fallo el uso del DAOLECCION: " + ex.getMessage());
                    }
        } else {
            System.out.println("FALLO: Algún parámetro es null");
        }
             
        return false;
    }

    public List<DtoLeccionLista> obtenerListaLeccin(int id_modulo) {
    System.out.println("obtener lista leccioens - ID recibido: " + id_modulo);
    
        if(id_modulo > 0){
            List<DtoLeccionLista> listaLeccionDto = new ArrayList<>();
            try {
                List<Leccion> listLecciones = daoLeccion.listarPorId(id_modulo);
                    if(listLecciones != null){
                        for (Leccion leccion : listLecciones) {
                            listaLeccionDto.add(new DtoLeccionLista(leccion.getId_leccion(),
                                    leccion.getTitulo(), leccion.getUrl_video(),
                                    leccion.getDescripcion(),id_modulo));
                        }
                        return listaLeccionDto;
                    } 
                } catch (Exception ex) {
                    System.out.println("Error en la capa servicio: " + ex.getMessage());
                }
        } else {
            System.out.println("ID de modulo inválido: " + id_modulo);
        }
        return null;
    }

       
    
}
