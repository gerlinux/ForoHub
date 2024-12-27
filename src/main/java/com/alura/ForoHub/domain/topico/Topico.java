package com.alura.ForoHub.domain.topico;

import com.alura.ForoHub.domain.usuario.DatosRegistroUsuario;
import com.alura.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String titulo;

    private String mensaje;

    private String curso;

    private LocalDateTime fecha;

    private Boolean status;

    public void desactivarTopico() {
        this.status = false;
    }

    //    public Topico(DatosRegistroTopico datos) {
//        this.activo = true;
//        this.usuario = datos.idUsuario();
//        this.titulo= datos.titulo();
//        this.mensaje = datos.mensaje();
//        this.fecha = datos.fecha();
//    }
    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.curso() != null) {
            this.curso = datosActualizarTopico.curso();
        }
    }
}
