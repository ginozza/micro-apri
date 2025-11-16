<%@page import="dto.DtoMatEducativo"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="java.lang.reflect.Type"%>
<%@page import="utilidad.Ruta"%>
<%@page import="dto.DtoUsuarioLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Explorar Materiales - Sistema Educativo</title>
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="css/styleUserDash.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
    
    <style>
        .explorar-container {
            background: white;
            border-radius: 12px;
            padding: 30px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        
        .search-bar {
            display: flex;
            gap: 15px;
            margin-bottom: 30px;
            flex-wrap: wrap;
        }
        
        .search-input, .filter-select {
            padding: 12px 20px;
            border: 2px solid #e2e8f0;
            border-radius: 8px;
            font-size: 15px;
        }
        
        .search-input {
            flex: 1;
            min-width: 250px;
        }
        
        .filter-select {
            min-width: 180px;
        }
        
        .search-input:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }
        
        .stats-cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        
        .stat-card {
            color: white;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }
        
        .stat-card.libros { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }
        .stat-card.articulos { background: linear-gradient(135deg, #48bb78 0%, #38a169 100%); }
        .stat-card.cursos { background: linear-gradient(135deg, #ed8936 0%, #dd6b20 100%); }
        
        .stat-card h3 {
            margin: 0;
            font-size: 32px;
            font-weight: bold;
        }
        
        .stat-card p {
            margin: 5px 0 0 0;
            font-size: 14px;
            opacity: 0.9;
        }
        
        .materiales-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
            gap: 20px;
        }
        
        .material-card {
            background: white;
            border: 2px solid #e2e8f0;
            border-radius: 12px;
            padding: 20px;
            transition: all 0.3s;
        }
        
        .material-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            border-color: #667eea;
        }
        
        .material-header {
            display: flex;
            justify-content: space-between;
            align-items: start;
            margin-bottom: 15px;
        }
        
        .material-icon {
            font-size: 32px;
            color: #667eea;
        }
        
        .material-badge {
            padding: 5px 12px;
            border-radius: 20px;
            font-size: 11px;
            font-weight: 600;
            text-transform: uppercase;
        }
        
        .badge-libro { background: #bee3f8; color: #2c5282; }
        .badge-articulo { background: #c6f6d5; color: #22543d; }
        .badge-curso { background: #fed7d7; color: #742a2a; }
        
        .material-title {
            font-size: 18px;
            font-weight: 600;
            color: #2d3748;
            margin: 10px 0;
        }
        
        .material-category {
            display: inline-block;
            background: #f7fafc;
            padding: 4px 10px;
            border-radius: 6px;
            font-size: 13px;
            color: #4a5568;
            margin-bottom: 10px;
        }
        
        .material-description {
            color: #718096;
            font-size: 14px;
            line-height: 1.6;
            margin: 10px 0;
            display: -webkit-box;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }
        
        .material-meta {
            display: flex;
            gap: 15px;
            margin: 15px 0;
            font-size: 13px;
            color: #718096;
        }
        
        .material-meta span {
            display: flex;
            align-items: center;
            gap: 5px;
        }
        
        .btn-info {
            width: 100%;
            padding: 10px;
            background: #667eea;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 14px;
            font-weight: 500;
            transition: all 0.3s;
        }
        
        .btn-info:hover {
            background: #5568d3;
            transform: translateY(-2px);
        }
        
        .btn-download {
            padding: 10px 20px;
            background: #48bb78;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 14px;
        }
        
        .btn-download:hover {
            background: #38a169;
        }
        
        .empty-state, .loading {
            text-align: center;
            padding: 60px 20px;
            color: #718096;
        }
        
        .empty-state i, .loading i {
            font-size: 64px;
            opacity: 0.3;
            margin-bottom: 20px;
        }
        
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.7);
            z-index: 1000;
            justify-content: center;
            align-items: center;
        }
        
        .modal.active {
            display: flex;
        }
        
        .modal-content {
            background: white;
            border-radius: 12px;
            max-width: 700px;
            width: 90%;
            max-height: 90vh;
            overflow-y: auto;
        }
        
        .modal-header-detail {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            border-radius: 12px 12px 0 0;
        }
        
        .modal-header-detail h2 {
            margin: 0 0 10px 0;
            font-size: 24px;
        }
        
        .modal-body {
            padding: 30px;
        }
        
        .detail-row {
            display: flex;
            padding: 15px 0;
            border-bottom: 1px solid #e2e8f0;
        }
        
        .detail-label {
            font-weight: 600;
            color: #2d3748;
            min-width: 150px;
        }
        
        .detail-value {
            color: #4a5568;
            flex: 1;
        }
        
        .modal-footer {
            padding: 20px 30px;
            background: #f7fafc;
            border-radius: 0 0 12px 12px;
            display: flex;
            gap: 10px;
            justify-content: flex-end;
        }
        
        .add-cancel-btn {
            padding: 10px 20px;
            background: #718096;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 14px;
        }
        
        .add-cancel-btn:hover {
            background: #4a5568;
        }
    </style>
</head>
<body>
    <%
            String usuarioJson = request.getParameter("usuario");
            String listaJson = request.getParameter("lista");
            
            System.out.println("usuario: "+usuarioJson);
            System.out.println("ListajSON: "+listaJson);
            
            Gson gson = new Gson();
            
            DtoUsuarioLogin dtoUser = gson.fromJson(usuarioJson,DtoUsuarioLogin.class);
            
            Type tipoLista = new TypeToken<List<DtoMatEducativo>>(){}.getType();
            
            List<DtoMatEducativo> listaMat = gson.fromJson(listaJson,tipoLista);
            
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", dtoUser);
            sesion.setAttribute("listaMat", listaMat);
            
            // Convertir la lista a JSON para usarla en JavaScript
            String listaMaterialesJson = gson.toJson(listaMat);
    %>
    
    <div class="container">
        <aside class="sidebar">
            <div class="logo">
                <img src="img/Logo2.png" width="190" height="150" alt="Logo pagina" />
            </div>
            <nav>
                <a href="<%=Ruta.MS_USUARIO_URL%>/UsuarioControll?accion=dashboardUser" class="menu-item">Dashboard</a>
                <a href="<%=Ruta.MS_USUARIO_URL%>/CerrarSesion?accion=user" class="menu-item">Cerrar sesión</a>
            </nav>
        </aside>

        <main class="main-content">
            <header class="header">
                <div class="welcome-text">
                    <h1><i class="fa-solid fa-book-open"></i> Explorar Materiales Educativos</h1>
                    <p>Descubre libros, artículos y cursos disponibles</p>
                </div>
                <div class="header-right">
                    <span style="color: #718096;">
                        <i class="fa-solid fa-user-circle"></i> <%= dtoUser.primerNombre() %>
                    </span>
                </div>
            </header>

            <div class="explorar-container">
                <div class="stats-cards">
                    <div class="stat-card libros">
                        <h3 id="count-libros">0</h3>
                        <p><i class="fa-solid fa-book"></i> Libros</p>
                    </div>
                    <div class="stat-card articulos">
                        <h3 id="count-articulos">0</h3>
                        <p><i class="fa-solid fa-file-lines"></i> Artículos</p>
                    </div>
                    <div class="stat-card cursos">
                        <h3 id="count-cursos">0</h3>
                        <p><i class="fa-solid fa-graduation-cap"></i> Cursos</p>
                    </div>
                </div>

                <div class="search-bar">
                    <input type="text" 
                           id="search-input" 
                           class="search-input" 
                           placeholder="🔍 Buscar por nombre o descripción..."
                           onkeyup="filtrarMateriales()">
                    
                    <select id="filter-tipo" class="filter-select" onchange="filtrarMateriales()">
                        <option value="">Todos los tipos</option>
                        <option value="libro">📖 Libros</option>
                        <option value="articulo">📄 Artículos</option>
                        <option value="curso">🎓 Cursos</option>
                    </select>
                    
                    <select id="filter-categoria" class="filter-select" onchange="filtrarMateriales()">
                        <option value="">Todas las categorías</option>
                        <option value="Fisica">Física</option>
                        <option value="Matematicas">Matemáticas</option>
                        <option value="Ciencias">Ciencias</option>
                        <option value="Programacion">Programación</option>
                        <option value="Informatica">Informática</option>
                        <option value="Lingüística">Lingüística</option>
                        <option value="Literatura">Literatura</option>
                        <option value="Electronica">Electrónica</option>
                        <option value="Ingenieria">Ingeniería</option>
                        <option value="Historia">Historia</option>
                        <option value="Otros">Otros</option>
                    </select>
                </div>

                <div id="materiales-container">
                    <div class="loading">
                        <i class="fa-solid fa-spinner fa-spin fa-3x"></i>
                        <p>Cargando materiales educativos...</p>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <div id="modal-detalles" class="modal">
        <div class="modal-content">
            <div class="modal-header-detail">
                <h2 id="modal-titulo"></h2>
                <span id="modal-badge" class="material-badge"></span>
            </div>
            <div class="modal-body" id="modal-body"></div>
            <div class="modal-footer">
                <button class="btn-download" onclick="descargarMaterial()">
                    <i class="fa-solid fa-download"></i> Descargar
                </button>
                <button class="add-cancel-btn" onclick="cerrarModal()">
                    <i class="fa-solid fa-times"></i> Cerrar
                </button>
            </div>
        </div>
    </div>

    <script>
        // Cargar la lista de materiales desde el servidor (JSP)
        let materiales = <%= listaMaterialesJson %>;
        let materialesFiltrados = [...materiales];
        let materialSeleccionado = null;

        // Cargar materiales al iniciar la página
        window.onload = function() {
            cargarMateriales();
        };

        function cargarMateriales() {
            try {
                // Los materiales ya están cargados desde el JSP
                if (!materiales || materiales.length === 0) {
                    mostrarError('No hay materiales disponibles');
                    return;
                }
                
                materialesFiltrados = [...materiales];
                actualizarEstadisticas();
                renderizarMateriales();
            } catch (error) {
                console.error('Error:', error);
                mostrarError('Error al procesar materiales');
            }
        }

        function actualizarEstadisticas() {
            const libros = materiales.filter(m => m.tipo && m.tipo.toLowerCase() === 'libro').length;
            const articulos = materiales.filter(m => m.tipo && m.tipo.toLowerCase() === 'articulo').length;
            const cursos = materiales.filter(m => m.tipo && m.tipo.toLowerCase() === 'curso').length;
            
            document.getElementById('count-libros').textContent = libros;
            document.getElementById('count-articulos').textContent = articulos;
            document.getElementById('count-cursos').textContent = cursos;
        }

        function renderizarMateriales() {
            const container = document.getElementById('materiales-container');
            
            if (materialesFiltrados.length === 0) {
                container.innerHTML = 
                    '<div class="empty-state">' +
                    '<i class="fa-solid fa-search"></i>' +
                    '<h3>No se encontraron materiales</h3>' +
                    '<p>Intenta con otros filtros de búsqueda</p>' +
                    '</div>';
                return;
            }
            
            const grid = document.createElement('div');
            grid.className = 'materiales-grid';
            
            materialesFiltrados.forEach(material => {
                grid.appendChild(crearTarjetaMaterial(material));
            });
            
            container.innerHTML = '';
            container.appendChild(grid);
        }

        function crearTarjetaMaterial(material) {
            const card = document.createElement('div');
            card.className = 'material-card';
            
            const iconos = {
                'libro': 'fa-book',
                'articulo': 'fa-file-lines',
                'curso': 'fa-graduation-cap'
            };
            const tipoMaterial = material.tipo ? material.tipo.toLowerCase() : 'libro';
            const icon = iconos[tipoMaterial] || 'fa-file';
            
            const anio = material.anioPublicacion ? 
                new Date(material.anioPublicacion).getFullYear() : 'N/A';
            
            const descripcion = material.descripcion || 'Sin descripción disponible';
            
            card.innerHTML = 
                '<div class="material-header">' +
                '<i class="fa-solid ' + icon + ' material-icon"></i>' +
                '<span class="material-badge badge-' + tipoMaterial + '">' + tipoMaterial + '</span>' +
                '</div>' +
                '<h3 class="material-title">' + escapeHtml(material.nombre) + '</h3>' +
                '<span class="material-category">' +
                '<i class="fa-solid fa-tag"></i> ' + escapeHtml(material.categoria) +
                '</span>' +
                '<p class="material-description">' + escapeHtml(descripcion) + '</p>' +
                '<div class="material-meta">' +
                '<span><i class="fa-solid fa-calendar"></i> ' + anio + '</span>' +
                getMeta(material) +
                '</div>' +
                '<button class="btn-info">' +
                '<i class="fa-solid fa-info-circle"></i> Ver más información' +
                '</button>';
            
            card.querySelector('.btn-info').onclick = () => mostrarDetalles(material);
            
            return card;
        }

        function getMeta(material) {
            const tipoMaterial = material.tipo ? material.tipo.toLowerCase() : '';
            
            if (tipoMaterial === 'libro') {
                return '<span><i class="fa-solid fa-file"></i> ' + (material.cantidadPaginas || 'N/A') + ' págs</span>';
            } else if (tipoMaterial === 'articulo') {
                return '<span><i class="fa-solid fa-bookmark"></i> Vol. ' + (material.volumen || 'N/A') + '</span>';
            } else if (tipoMaterial === 'curso') {
                return '<span><i class="fa-solid fa-clock"></i> ' + (material.duracion || 'N/A') + '</span>';
            }
            return '';
        }

        function filtrarMateriales() {
            const texto = document.getElementById('search-input').value.toLowerCase();
            const tipo = document.getElementById('filter-tipo').value.toLowerCase();
            const categoria = document.getElementById('filter-categoria').value;
            
            materialesFiltrados = materiales.filter(m => {
                const nombreMaterial = m.nombre ? m.nombre.toLowerCase() : '';
                const descripcionMaterial = m.descripcion ? m.descripcion.toLowerCase() : '';
                const categoriaMaterial = m.categoria ? m.categoria.toLowerCase() : '';
                
                const matchTexto = nombreMaterial.includes(texto) ||
                                 descripcionMaterial.includes(texto) ||
                                 categoriaMaterial.includes(texto);
                
                const tipoMaterial = m.tipo ? m.tipo.toLowerCase() : '';
                const matchTipo = !tipo || tipoMaterial === tipo;
                const matchCategoria = !categoria || m.categoria === categoria;
                
                return matchTexto && matchTipo && matchCategoria;
            });
            
            renderizarMateriales();
        }

        function mostrarDetalles(material) {
            materialSeleccionado = material;
            
            document.getElementById('modal-titulo').textContent = material.nombre || 'Sin título';
            const tipoMaterial = material.tipo ? material.tipo.toLowerCase() : 'libro';
            document.getElementById('modal-badge').textContent = tipoMaterial;
            document.getElementById('modal-badge').className = 'material-badge badge-' + tipoMaterial;
            
            let html = '';
            html += crearDetalle('Categoría', material.categoria || 'N/A');
            html += crearDetalle('Descripción', material.descripcion || 'Sin descripción');
            
            if (material.anioPublicacion) {
                html += crearDetalle('Año de Publicación', 
                    new Date(material.anioPublicacion).toLocaleDateString('es-ES'));
            }
            
            if (tipoMaterial === 'libro') {
                html += crearDetalle('Editorial', material.editorial || 'N/A');
                html += crearDetalle('Edición', material.edicion || 'N/A');
                html += crearDetalle('Páginas', material.cantidadPaginas || 'N/A');
            } else if (tipoMaterial === 'articulo') {
                html += crearDetalle('Volumen', material.volumen || 'N/A');
                html += crearDetalle('Páginas', material.cantidadPaginas || 'N/A');
            } else if (tipoMaterial === 'curso') {
                html += crearDetalle('Duración', material.duracion || 'N/A');
            }
            
            const estadoHtml = material.estado ? 
                '<span style="color: #48bb78;"><i class="fa-solid fa-check-circle"></i> Disponible</span>' : 
                '<span style="color: #f56565;"><i class="fa-solid fa-times-circle"></i> No disponible</span>';
            html += crearDetalle('Estado', estadoHtml);
            
            document.getElementById('modal-body').innerHTML = html;
            document.getElementById('modal-detalles').classList.add('active');
        }

        function crearDetalle(label, value) {
            return '<div class="detail-row">' +
                   '<div class="detail-label"><i class="fa-solid fa-angle-right"></i> ' + label + ':</div>' +
                   '<div class="detail-value">' + value + '</div>' +
                   '</div>';
        }

        function descargarMaterial() {
            if (!materialSeleccionado) return;
            
            Swal.fire({
                title: 'Descargando...',
                text: 'Preparando descarga de "' + materialSeleccionado.nombre + '"',
                icon: 'info',
                timer: 2000,
                timerProgressBar: true,
                showConfirmButton: false
            }).then(() => {
                Swal.fire({
                    title: '¡Descarga iniciada!',
                    text: 'El material se está descargando',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            });
        }

        function cerrarModal() {
            document.getElementById('modal-detalles').classList.remove('active');
            materialSeleccionado = null;
        }

        window.onclick = function(event) {
            if (event.target === document.getElementById('modal-detalles')) {
                cerrarModal();
            }
        }

        document.addEventListener('keydown', function(event) {
            if (event.key === 'Escape') cerrarModal();
        });

        function escapeHtml(text) {
            if (!text) return '';
            const div = document.createElement('div');
            div.textContent = text;
            return div.innerHTML;
        }

        function mostrarError(mensaje) {
            document.getElementById('materiales-container').innerHTML = 
                '<div class="empty-state">' +
                '<i class="fa-solid fa-exclamation-triangle"></i>' +
                '<h3>Error al cargar materiales</h3>' +
                '<p>' + mensaje + '</p>' +
                '</div>';
        }
    </script>
</body>
</html>