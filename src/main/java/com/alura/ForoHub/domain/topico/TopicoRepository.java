package com.alura.ForoHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t WHERE t.status = true ORDER BY t.fecha DESC")
    Page<Topico> findAllTopics(Pageable paginacion);

//    Page<Topico> findTop10ByStatusTrueByOrderByFechaDesc(Pageable paginacion);

}
