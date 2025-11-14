
package persistencia;

import dto.DtoArticuloRegistro;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Articulo;


public class DaoArticuloImpPostgres implements DaoArticulo{

    @Override
    public boolean registrar(DtoArticuloRegistro articulo, InputStream inputStream) throws Exception {
        String sql = "INSERT INTO articulos(categoria, descripcion, nombre, anio_publicacion, estado, tipo, volumen, cantidad_paginas,id_usuario, archivopdf ) VALUES (?,?,?,?,?,?,?,?,?,?)";

               try(Connection conn = ConexionBD.getInstancia().getConexion();
                       PreparedStatement stmt = conn.prepareCall(sql)){
                System.out.println("Iniciando registro de libro: " + articulo.nombre());

                   stmt.setString(1, articulo.categoria());
                   stmt.setString(2, articulo.descripcion());
                   stmt.setString(3,articulo.nombre());
                   stmt.setDate(4, Date.valueOf(articulo.año_publicacion()));
                   stmt.setBoolean(5,true);
                   stmt.setString(6, articulo.tipo());
                   stmt.setInt(7,articulo.volumen());
                   stmt.setInt(8,articulo.cantPaginas());
                   stmt.setInt(9,articulo.id_usuario());
                   stmt.setBinaryStream(10, inputStream);

                   if(stmt.executeUpdate()>0){
                       return true;
                   }
               }catch (SQLException ex) {
               throw new ApriException("Error al insertar el articulo a la BD: " + ex.getMessage());
           }
        
        return false;
    }

    @Override
    public boolean actualizar(Articulo libro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Articulo libro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Articulo> listar() throws Exception {
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
