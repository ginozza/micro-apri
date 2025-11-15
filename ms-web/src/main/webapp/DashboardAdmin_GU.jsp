<%@page import="utilidad.Ruta"%>
<%@page import="dto.DtoUsuarioLogin"%>
<%@page import="dto.DtoAdminLogin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Administrador"%>
<%@page import="modelo.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html> 
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Administración-Gestion usuarios</title>
    <link rel="stylesheet" href="css/styleDashboardAdmin_1.css"/>

</head>
<body>
    <%
        HttpSession sesion = request.getSession(false);
        if(sesion == null || sesion.getAttribute("admin") == null){
            response.sendRedirect("InicioSesionUsuario.jsp");
            return;
        }
        
        DtoAdminLogin user_login = (DtoAdminLogin) sesion.getAttribute("admin");
    %>
    
    <div class="container">
        <aside class="sidebar">
            <div class="logo">
                <img src="img/logoAdmin.png" width="190" height="150" alt="Logo pagina" />
            </div>
            <nav>
                <a href="<%=Ruta.MS_USUARIO_URL%>/UsuarioControll?accion=dashboardAdmin" class="menu-item">Dashboard</a>
                <a href="DashboardAdmin_GU.jsp" class="menu-item active">Gestionar Usuarios</a>               
                <a href="DashboardAdmin_GR.jsp" class="menu-item">Gestionar Reportes</a>
                <a href="<%=Ruta.MS_USUARIO_URL%>/CerrarSesion?accion=admin" class="menu-item">Cerrar Sesión</a>
            </nav>
        </aside>

        <main class="main-content">
            <header class="header">
                <div class="welcome-text">
                    <h1>Panel de Administración</h1>
                    <p>Bienvenido, <%=user_login.primerNombre()%> - Gestiona tu plataforma</p>
                </div>
            </header>

               <div class="search-bar">
                    <input type="text" class="search-input" placeholder="Buscar por nombre, email o ID de usuario...">
                    <button class="search-btn">🔍 Buscar</button>
                </div>

                <%
                    List<DtoUsuarioLogin> listaUsuarios = (List) sesion.getAttribute("listaU");
                %>
                <div class="table-container">
                    <div class="table-header">
                        <h2 class="table-title">Lista de Usuarios</h2>
                        <span class="table-info">Mostrando <%=listaUsuarios.size()%> usuarios</span>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre Completo</th>
                                <th>Email</th>
                                <th>Fecha de Registro</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>

                            <%
                            for (DtoUsuarioLogin user : listaUsuarios) {%>
                            <tr>
                                
                                <td><%=user.id_persona()%></td>
                                <td><%=user.primerNombre()%></td>
                                <td><%=user.correo()%></td>
                                <%if(!user.estado()){%>
                                 <td><span class="status-badge status-inactive">Inactivo</span></td>
                                <%}else{%>
                                 <td><span class="status-badge status-active">Activo</span></td>
                                <%}%>
                                <td>
                                    <a href="EditarUsuario?id=1" class="action-btn edit-btn">✏️ Editar</a>
                                    <a href="CerrarSesion" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de eliminar este usuario?')">🗑️ Eliminar</a>
                                </td>
                            </tr>
                            <%}%>
                                
                        </tbody>
                    </table>
                </div>
            </div>

        </main>
    </div>
</body>
</html>