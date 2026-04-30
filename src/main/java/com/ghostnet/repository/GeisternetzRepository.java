package com.ghostnet.repository;

import com.ghostnet.entity.Geisternetz;
import com.ghostnet.enums.GeisternetzStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeisternetzRepository extends JpaRepository<Geisternetz, Long> {
    List<Geisternetz> findByStatus(GeisternetzStatus status);
    List<Geisternetz> findByBergendePersonIsNotNull();
    List<Geisternetz> findByStatusNot(GeisternetzStatus status);
}
