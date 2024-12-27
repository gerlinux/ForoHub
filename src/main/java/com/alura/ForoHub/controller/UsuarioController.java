package com.alura.ForoHub.controller;

import com.alura.ForoHub.domain.usuario.DatosDetalleUsuario;
import com.alura.ForoHub.domain.usuario.DatosRegistroUsuario;
import com.alura.ForoHub.domain.usuario.Usuario;
import com.alura.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    //@SecurityRequirement(name = "bearer-key") //Relacionado con SpringDcoCOnfiguration
    public void registrar(@RequestBody @Valid DatosRegistroUsuario datos) {
        repository.save(new Usuario(datos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleUsuario> retornarUsuario(@PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);
        var datosUsuario = new DatosDetalleUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getActivo(), usuario.getClave(), usuario.getLogin());
        return ResponseEntity.ok(datosUsuario);
    }
    @GetMapping
    public Page<DatosDetalleUsuario> listarUsuarios(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(DatosDetalleUsuario::new);
    }
}
