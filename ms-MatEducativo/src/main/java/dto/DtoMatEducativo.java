
package dto;


public record DtoMatEducativo(
    int id_materialEducativo,
    String nombre,
    String categoria,
    String año_publicacion,
    String descripcion,
    String tipo
) {}
