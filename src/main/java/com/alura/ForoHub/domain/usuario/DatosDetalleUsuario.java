package com.alura.ForoHub.domain.usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email,
        Boolean activo,
        String login,
        String clave
) {
    public DatosDetalleUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getActivo(), usuario.getLogin(), usuario.getClave());
    }
}
