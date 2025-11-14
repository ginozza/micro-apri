
package servicio;

import dto.DtoCursoRegistro;
import persistencia.ApriException;
import persistencia.DaoCurso;
import persistencia.DaoCursoImpPostgres;



public class CursoServicio {
    
    DaoCurso daoCurso;
    
    
    public CursoServicio(){
        daoCurso = new DaoCursoImpPostgres();
    }
    
    
    
    
    
    public int subirCurso(DtoCursoRegistro cursoDto) throws Exception {
    
        if(cursoDto != null && cursoDto.id_usuario()>0 && cursoDto.duracion()>0){
            System.out.println("Paso los filtros del curso servicio");
                    try {
                        return daoCurso.registrar(cursoDto);
                    } catch (Exception ex) {
                        System.err.println("Error en DAO: " + ex.getMessage());
                        throw new ApriException("Fallo el uso del DAOCURSO: " + ex.getMessage());
                    }
        } else {
            System.out.println("FALLO: Algún parámetro es null");
        }
        return -1;
        }

       
    
}
