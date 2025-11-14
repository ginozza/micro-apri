/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentacion;

import com.google.gson.Gson;
import dto.DtoModuloLista;
import dto.DtoModuloRegistrar;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicio.ModuloServicio;
import utilidad.Ruta;


@WebServlet(name = "ModuloControll", urlPatterns = {"/ModuloControll"})
public class ModuloControll extends HttpServlet {
    private ModuloServicio moduloServicio;
    private Gson gson;
    
    @Override
    public void init(){
        moduloServicio = new ModuloServicio();
        gson = new Gson();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        System.out.println("GUARDAMOS LA ACCION: "+accion);
        int id_curso= Integer.parseInt(request.getParameter("idCurso"));

        if(accion==null || id_curso<0){
            response.sendRedirect(Ruta.MS_WEB+"/InicioSesionUsuario.jsp");
            return;
        }
        
        switch (accion) {
            case "listarModulos" -> listarModulos(request,response,id_curso);
            
            default -> throw new AssertionError();
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("accion")==null){
            response.sendRedirect(Ruta.MS_WEB+"/RegistrarUsuario.jsp?error=No se pudo establecer que accion realizar");
            return;
        }
        
        String accion = request.getParameter("accion");
        System.out.println("Accion del DOPOST libro : "+accion);
        
        switch (accion) {
            case "register" -> registrarModulo(request,response);
            default -> throw new AssertionError();
        }
       
    }
    
    @Override
    public String getServletInfo() {
        return "";
    }

    private void registrarModulo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    {
        
        
        String nombre = request.getParameter("nombre");
        System.out.println("Nombre del modulo: " + nombre);
        int id_curso = Integer.parseInt(request.getParameter("idCurso"));
        System.out.println("Id del usuario que va a guardar el curso: "+id_curso);
        DtoModuloRegistrar dtoMod = new DtoModuloRegistrar(1, nombre,id_curso);
        
        try {

            System.out.println("Id del modulo agregado: "+id_curso);
            if( moduloServicio.subirModulo(dtoMod)){            
                listarModulos(request, response, id_curso);
            } else {
                System.out.println("NO PUDO CREAR EL MODULO");
                listarModulos(request, response, id_curso);
            }
        } catch (NumberFormatException ex) {
                response.sendRedirect(Ruta.MS_WEB+"/RegistrarCurso.jsp?error=En formato de numero");

        } catch (Exception ex) {
                response.sendRedirect(Ruta.MS_WEB+"/RegistrarCurso.jsp?error=Error general");

        }
        
        
    }

    private void listarModulos(HttpServletRequest request, HttpServletResponse response, int id_curso) {
        
        List<DtoModuloLista> listModulosDto = moduloServicio.obtenerListaModulo(id_curso);
        if(listModulosDto!=null){
            try {
                System.out.println("LISTA DE MODULOS NO NULA");
                String listaJson = gson.toJson(listModulosDto);
                response.sendRedirect(Ruta.MS_WEB+"/GestionarModulosCurso.jsp"+"?idCurso="+id_curso+"&lista=" + URLEncoder.encode(listaJson, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ModuloControll.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ModuloControll.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    

  
}
