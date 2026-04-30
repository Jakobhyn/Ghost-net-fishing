package com.ghostnet.repository;

import com.ghostnet.entity.Meldung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeldungRepository extends JpaRepository<Meldung, Long> {}
