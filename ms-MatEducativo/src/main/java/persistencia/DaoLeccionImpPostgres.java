
package persistencia;

import dto.DtoLeccionLista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Leccion;


public class DaoLeccionImpPostgres implements DaoLeccion{

    @Override
    public boolean registrar(DtoLeccionLista leccionDto) throws Exception {
        String sql = "INSERT INTO lecciones (nombre,url_video,descripcion,id_modulo) VALUES (?,?,?,?)";

        try (Connection conn = ConexionBD.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("Iniciando registro de la leccion: " + leccionDto.nombre());

            stmt.setString(1,leccionDto.nombre());
            stmt.setString(2, leccionDto.url_video());
            stmt.setString(3, leccionDto.descripcion());
            stmt.setInt(4,leccionDto.id_modulo());
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                throw new ApriException("No se pudo insertar la leccion");
            }

        } catch (SQLException ex) {
            throw new ApriException("Error al insertar la leccion a la BD: " + ex.getMessage());
        }
        
    }

    @Override
    public List<Leccion> listarPorId(int id_modulo) throws Exception {
    
        List<Leccion> listaL = new ArrayList<>();
        
        String sql = "SELECT * FROM lecciones WHERE id_modulo = ?";
        
        try(Connection conn =  ConexionBD.getInstancia().getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id_modulo);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
               int id_leccion = rs.getInt("id_leccion");
               String nombre = rs.getString("nombre");
               String url_video = rs.getString("url_video");
               String descripcion = rs.getString("descripcion");
               listaL.add(new Leccion(id_leccion, nombre, url_video,descripcion));                               
            }
            
        } catch (SQLException e) {
            System.err.println("Error SQL al consultar modulos: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error: algún valor obtenido del ResultSet es nulo. " + e.getMessage());
        }catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        return listaL;
    
    }

    @Override
    public boolean actualizar(Leccion leccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Leccion leccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Leccion> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean cambiarEstadoF(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean cambiarEstadoT(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
   
    

}
