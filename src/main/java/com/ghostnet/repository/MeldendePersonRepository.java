package com.ghostnet.repository;

import com.ghostnet.entity.MeldendePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeldendePersonRepository extends JpaRepository<MeldendePerson, Long> {}
