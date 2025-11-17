
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MaterialEducativo {
    
    private int id_materialEducativo;
    private String categoria;
    private String nombre;
    private LocalDate año_publicacion;
    private String tipo;
    private String descripcion;
    private boolean estado;
    private int  id_usuario;
    
    public MaterialEducativo() {
    }

    public MaterialEducativo(int id_materialEducativo, String categoria, String nombre, LocalDate año_publicacion, String tipo, String descripcion, boolean estado) {
        this.id_materialEducativo = id_materialEducativo;
        this.categoria = categoria;
        this.nombre = nombre;
        this.año_publicacion = año_publicacion;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "id_materialEducativo=" + id_materialEducativo + ", categoria=" + categoria + ", nombre=" + nombre + ", anio_publicacion=" + año_publicacion + ", tipo=" + tipo + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }


    
    
    

   
    public int getId_materialEducativo() {
        return id_materialEducativo;
    }

    /**
     * @param id_materialEducativo the id_materialEducativo to set
     */
    public void setId_materialEducativo(int id_materialEducativo) {
        this.id_materialEducativo = id_materialEducativo;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the año_publicacion
     */
    public LocalDate getAño_publicacion() {
        return año_publicacion;
    }

    /**
     * @param año_publicacion the año_publicacion to set
     */
    public void setAño_publicacion(LocalDate año_publicacion) {
        this.año_publicacion = año_publicacion;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
    
    
    
}
