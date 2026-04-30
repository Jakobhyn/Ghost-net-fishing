package com.ghostnet.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meldung")
public class Meldung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "geisternetz_id", nullable = false)
    private Geisternetz geisternetz;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "meldende_person_id")
    private MeldendePerson meldendePerson;

    @Column(nullable = false)
    private LocalDateTime gemeldetAm = LocalDateTime.now();

    public Meldung() {}

    public Meldung(Geisternetz geisternetz, MeldendePerson meldendePerson) {
        this.geisternetz = geisternetz;
        this.meldendePerson = meldendePerson;
        this.gemeldetAm = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Geisternetz getGeisternetz() { return geisternetz; }
    public void setGeisternetz(Geisternetz geisternetz) { this.geisternetz = geisternetz; }

    public MeldendePerson getMeldendePerson() { return meldendePerson; }
    public void setMeldendePerson(MeldendePerson meldendePerson) { this.meldendePerson = meldendePerson; }

    public LocalDateTime getGemeldetAm() { return gemeldetAm; }
    public void setGemeldetAm(LocalDateTime gemeldetAm) { this.gemeldetAm = gemeldetAm; }
}
