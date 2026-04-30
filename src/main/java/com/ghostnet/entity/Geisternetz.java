package com.ghostnet.entity;

import com.ghostnet.enums.GeisternetzStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "geisternetz")
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String groesse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeisternetzStatus status = GeisternetzStatus.GEMELDET;

    @ManyToOne
    @JoinColumn(name = "bergende_person_id")
    private BergendePerson bergendePerson;

    public Geisternetz() {}

    public Geisternetz(Double latitude, Double longitude, String groesse) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.groesse = groesse;
        this.status = GeisternetzStatus.GEMELDET;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getGroesse() { return groesse; }
    public void setGroesse(String groesse) { this.groesse = groesse; }

    public GeisternetzStatus getStatus() { return status; }
    public void setStatus(GeisternetzStatus status) { this.status = status; }

    public BergendePerson getBergendePerson() { return bergendePerson; }
    public void setBergendePerson(BergendePerson bergendePerson) { this.bergendePerson = bergendePerson; }
}
