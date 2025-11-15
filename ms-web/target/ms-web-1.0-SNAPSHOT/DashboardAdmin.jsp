<%@page import="dto.DtoUsuarioLogin"%>
<%@page import="dto.DtoAdminLogin"%>
<%@page import="utilidad.Ruta"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="java.lang.reflect.Type"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Administrador"%>
<%@page import="modelo.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html> 
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Administración</title>
    <link rel="stylesheet" href="css/styleDashboardAdmin_1.css"/>

</head>
<body>
    <%
            String usuarioJson = request.getParameter("admin");
            String listaJson = request.getParameter("lista");
            
            System.out.println("Admin "+usuarioJson);
            System.out.println("ListajSON: "+listaJson);
            
            Gson gson = new Gson();
            
            DtoAdminLogin dtoUser = gson.fromJson(usuarioJson,DtoAdminLogin.class);
            
            Type tipoLista = new TypeToken<List<DtoUsuarioLogin>>(){}.getType();
            
            List<DtoUsuarioLogin> listaU = gson.fromJson(listaJson,tipoLista);
            
            HttpSession sesion = request.getSession();
            sesion.setAttribute("admin", dtoUser);
            sesion.setAttribute("listaU", listaU); 
       
        int n=0;
        
        for (DtoUsuarioLogin usr : listaU) {
                if(usr.estado()){
                    n++;
               } 
        }
    %>
    
    <div class="container">
        <aside class="sidebar">
            <div class="logo">
                <img src="img/logoAdmin.png" width="190" height="150" alt="Logo pagina" />
            </div>
            <nav>
  
                <a href="<%=Ruta.MS_USUARIO_URL%>/UsuarioControll?accion=dashboardAdmin" class="menu-item active">Dashboard</a>
                <a href="DashboardAdmin_GU.jsp" class="menu-item">Gestionar Usuarios</a>                </form>
                <a href="DashboardAdmin_GR.jsp" class="menu-item">Gestionar Reportes</a>
                <a href="<%=Ruta.MS_USUARIO_URL%>/CerrarSesion?accion=admin"  class="menu-item">Cerrar Sesión</a>                    
            </nav>
        </aside>

        <main class="main-content">
            <header class="header">
                <div class="welcome-text">
                    <h1>Panel de Administración</h1>
                    <p>Bienvenido, <%=dtoUser.primerNombre()%> - Gestiona tu plataforma</p>
                </div>
            </header>

            <!-- SECCIÓN: GESTIONAR USUARIOS -->
            <div id="usuarios-section" class="content-section">
                <div class="stats-grid">
                    <div class="stat-card">
                        <div class="stat-icon blue">👥</div>
                        <div class="stat-info">
                            <h3>Total Usuarios</h3>
                            <p><%=listaU.size()%></p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon green">✓</div>
                        <div class="stat-info">
                            <h3>Usuarios Activos</h3>
                            <p><%=n%></p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-icon red">✗</div>
                        <div class="stat-info">
                            <h3>Usuarios Inactivos</h3>
                            <p><%=listaU.size()-n%></p>
                        </div>
                    </div>
                </div>

        </main>
    </div>
</body>
</html>