/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentacion;

import com.google.gson.Gson;
import dto.DtoCursoRegistro;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicio.CursoServicio;
import utilidad.Ruta;


@WebServlet(name = "CursoControll", urlPatterns = {"/CursoControll"})
public class CursoControll extends HttpServlet {
    private CursoServicio cursoServicio;
    private Gson gson;
    
    @Override
    public void init(){
        cursoServicio = new CursoServicio();
        gson = new Gson();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
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
            case "register" -> registrarCurso(request,response);
            default -> throw new AssertionError();
        }
       
    }
    
    @Override
    public String getServletInfo() {
        return "";
    }

    private void registrarCurso(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    {
        
        
        String nombre = request.getParameter("nombre");
        String categoria = request.getParameter("categoria");
        String descripcion = request.getParameter("descripcion");
        String año_publi = request.getParameter("año_publicacion");
        int duracion = Integer.parseInt(request.getParameter("duracion"));
        String nivel =request.getParameter("nivel");



        System.out.println("Nombre del curso: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Categoría: " + categoria);
        System.out.println("nivel: " + nivel);
        System.out.println("Año de publicación (String): " + año_publi);
        System.out.println("duracion: " + duracion);

        int id_usuario = Integer.parseInt(request.getParameter("idUsuario"));
        System.out.println("Id del usuario que va a guardar el curso: "+id_usuario);
        DtoCursoRegistro dtoCurso = new DtoCursoRegistro(1, id_usuario, nombre,
                categoria, descripcion, año_publi, duracion, nivel,"curso");
        
        try {
            int id_curso = cursoServicio.subirCurso(dtoCurso);
            System.out.println("Id del curso agregado: "+id_curso);
            if(id_curso!=-1){
                
                response.sendRedirect(Ruta.MS_WEB+"/RegistrarCurso.jsp?mensaje=Ingreso exitoso&idCurso="+id_curso);

            } else {
                response.sendRedirect(Ruta.MS_WEB+"/RegistrarCurso.jsp?error=No se pudo subir el libro");
            }
        } catch (NumberFormatException ex) {
                response.sendRedirect(Ruta.MS_WEB+"/RegistrarCurso.jsp?error=En formato de numero");

        } catch (Exception ex) {
                response.sendRedirect(Ruta.MS_WEB+"/RegistrarCurso.jsp?error=Error general");

        }
        

    }
    

  
}
