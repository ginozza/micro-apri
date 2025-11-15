
package persistencia;

import dto.DtoModuloRegistrar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Modulo;


public class DaoModuloImpPostgres1 implements DaoModulo{

    @Override
    public boolean registrar(Modulo modulo, int id_curso) throws Exception {
        String sql = "INSERT INTO modulos (titulo,id_curso) VALUES (?,?)";

        try (Connection conn = ConexionBD.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("Iniciando registro del modulo: " + modulo.getTitulo());

            stmt.setString(1,modulo.getTitulo());
            stmt.setInt(2, id_curso);
            
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                throw new ApriException("No se pudo insertar el modulo");
            }

        } catch (SQLException ex) {
            throw new ApriException("Error al insertar el modulo a la BD: " + ex.getMessage());
        }
        
    }

    @Override
    public List<Modulo> listarPorId(int id_curso) throws Exception {
    
        List<Modulo> listaM = new ArrayList<>();
        
        String sql = "SELECT * FROM modulos WHERE id_curso = ?";
        
        try(Connection conn =  ConexionBD.getInstancia().getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id_curso);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
               int idModulo = rs.getInt("id_modulo");
               String titulo = rs.getString("titulo");
               listaM.add(new Modulo(idModulo, titulo));
               
                
            }
            
        } catch (SQLException e) {
            System.err.println("Error SQL al consultar modulos: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error: algún valor obtenido del ResultSet es nulo. " + e.getMessage());
        }catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        return listaM;
    
    }
    
    
    
    @Override
    public boolean actualizar(Modulo modulo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Modulo modulo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Modulo> listar() throws Exception {
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
