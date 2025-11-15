
package servicio;

import dto.DtoModuloLista;
import dto.DtoModuloRegistrar;
import java.util.ArrayList;
import java.util.List;

import modelo.Modulo;
import persistencia.ApriException;
import persistencia.DaoModulo;
import persistencia.DaoModuloImpPostgres1;



public class ModuloServicio {
    
    DaoModulo daoModulo;
    
    
    public ModuloServicio(){
        daoModulo = new DaoModuloImpPostgres1();
    }
    
    
    
    
    
    public boolean subirModulo(DtoModuloRegistrar moduloDto) throws Exception {
   
        if(moduloDto != null ){
            System.out.println("Paso los filtros del modulo servicio");
                    try {
                        Modulo mod = new Modulo(1,moduloDto.titulo());
                        return daoModulo.registrar(mod, moduloDto.id_curso());
                    } catch (Exception ex) {
                        System.err.println("Error en DAO: " + ex.getMessage());
                        throw new ApriException("Fallo el uso del DAOMODULO: " + ex.getMessage());
                    }
        } else {
            System.out.println("FALLO: Algún parámetro es null");
        }
             
        return false;
        }

    public List<DtoModuloLista> obtenerListaModulo(int id_curso) {
    System.out.println("obtenerListaModulo - ID recibido: " + id_curso);
    
        if(id_curso > 0){
            List<DtoModuloLista> listaModuloDto = new ArrayList<>();
            try {
                List<Modulo> listModulos = daoModulo.listarPorId(id_curso);
                System.out.println("Modulos encontrados: " + (listModulos != null ? listModulos.size() : 0));

                    if(listModulos != null){
                        for (Modulo listModulo : listModulos) {
                            listaModuloDto.add(new DtoModuloLista(
                                listModulo.getId_modulo(),
                                listModulo.getTitulo()
                            ));
                        }
                        return listaModuloDto;
                    } 
                } catch (Exception ex) {
                    System.out.println("Error en la capa servicio: " + ex.getMessage());
                }
        } else {
            System.out.println("ID de curso inválido: " + id_curso);
        }
        return null;
    }

       
    
}
