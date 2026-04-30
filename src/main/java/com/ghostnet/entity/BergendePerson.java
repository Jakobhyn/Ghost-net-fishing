package com.ghostnet.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bergende_person")
public class BergendePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String telefonnummer;

    @OneToMany(mappedBy = "bergendePerson")
    private List<Geisternetz> zugewieseneNetze;

    public BergendePerson() {}

    public BergendePerson(String name, String telefonnummer) {
        this.name = name;
        this.telefonnummer = telefonnummer;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTelefonnummer() { return telefonnummer; }
    public void setTelefonnummer(String telefonnummer) { this.telefonnummer = telefonnummer; }

    public List<Geisternetz> getZugewieseneNetze() { return zugewieseneNetze; }
    public void setZugewieseneNetze(List<Geisternetz> zugewieseneNetze) { this.zugewieseneNetze = zugewieseneNetze; }
}
