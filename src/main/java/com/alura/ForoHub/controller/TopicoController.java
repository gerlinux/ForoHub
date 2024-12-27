package com.alura.ForoHub.controller;

import com.alura.ForoHub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CargarTopico carga;

    //Obtiene los datos del frontend
    @PostMapping
    @Transactional
    public ResponseEntity cargar(@RequestBody @Valid DatosRegistroTopico datos) {

        var detalleConsulta = carga.cargar(datos);

        return ResponseEntity.ok(detalleConsulta);
    }
    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listarUsuarios(@PageableDefault(size = 3) Pageable paginacion) {
        //return ResponseEntity.ok(repository.findByStatusTrue(paginacion).map(DatosDetalleTopico::new));
        return ResponseEntity.ok(repository.findAllTopics(paginacion).map(DatosDetalleTopico::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getCurso(), topico.getUsuario().getId(), topico.getFecha());
        return ResponseEntity.ok(datosTopico);
    }
    @PutMapping("/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = repository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(), topico.getUsuario().getId(),  topico.getFecha()));
    }
    @DeleteMapping("/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
