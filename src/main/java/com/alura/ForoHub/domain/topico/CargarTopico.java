package com.alura.ForoHub.domain.topico;

import com.alura.ForoHub.domain.topico.validaciones.ValidacionException;
import com.alura.ForoHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CargarTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosDetalleTopico cargar(DatosRegistroTopico datos){

        if(!usuarioRepository.existsById(datos.idUsuario())){
            throw new ValidacionException("No existe un usuario con el id informado");
        }

        //validadores.forEach(v -> v.validar(datos));
        System.out.println("Antes de findById " + datos.idUsuario());
        var usuario = usuarioRepository.findById(datos.idUsuario()).get();
        System.out.println("Usuario es " + usuario.getId());
        var consulta = new Topico(null, usuario, datos.titulo(), datos.mensaje(), datos.curso(), datos.fecha(),true);
        topicoRepository.save(consulta);

        return new DatosDetalleTopico(consulta);
    }

}
