package com.alura.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String curso,
        Long usuario_id,
        LocalDateTime fecha
) {
}
