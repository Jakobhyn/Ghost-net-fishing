package com.ghostnet.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meldende_person")
public class MeldendePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String telefonnummer;

    @Column(nullable = false)
    private Boolean anonym = false;

    public MeldendePerson() {}

    public MeldendePerson(String name, String telefonnummer, Boolean anonym) {
        this.name = name;
        this.telefonnummer = telefonnummer;
        this.anonym = anonym;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTelefonnummer() { return telefonnummer; }
    public void setTelefonnummer(String telefonnummer) { this.telefonnummer = telefonnummer; }

    public Boolean getAnonym() { return anonym; }
    public void setAnonym(Boolean anonym) { this.anonym = anonym; }
}
