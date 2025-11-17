
package modelo;



public class Leccion {
    
    private int id_leccion;
    private String titulo;
    private String url_video;
    private String descripcion;

    public Leccion(int id_leccion, String titulo, String url_video, String descripcion) {
        this.id_leccion = id_leccion;
        this.titulo = titulo;
        this.url_video = url_video;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Leccion{" + "id_leccion=" + id_leccion + ", titulo=" + titulo + ", url_video=" + url_video + ", descripcion=" + descripcion + '}';
    }

    
    
    
    /**
     * @return the id_leccion
     */
    public int getId_leccion() {
        return id_leccion;
    }

    /**
     * @param id_leccion the id_leccion to set
     */
    public void setId_leccion(int id_leccion) {
        this.id_leccion = id_leccion;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the url_video
     */
    public String getUrl_video() {
        return url_video;
    }

    /**
     * @param url_video the url_video to set
     */
    public void setUrl_video(String url_video) {
        this.url_video = url_video;
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


}
