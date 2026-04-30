package com.ghostnet.service;

import com.ghostnet.entity.BergendePerson;
import com.ghostnet.entity.Geisternetz;
import com.ghostnet.enums.GeisternetzStatus;
import com.ghostnet.repository.BergendePersonRepository;
import com.ghostnet.repository.GeisternetzRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeisternetzService {

    private final GeisternetzRepository geisternetzRepository;
    private final BergendePersonRepository bergendePersonRepository;

    public GeisternetzService(GeisternetzRepository geisternetzRepository,
                               BergendePersonRepository bergendePersonRepository) {
        this.geisternetzRepository = geisternetzRepository;
        this.bergendePersonRepository = bergendePersonRepository;
    }

    public List<Geisternetz> alleNetze() {
        return geisternetzRepository.findAll();
    }

    public List<Geisternetz> zuBergendeNetze() {
        return geisternetzRepository.findByStatus(GeisternetzStatus.GEMELDET);
    }

    public List<Geisternetz> netzeWithBergendePerson() {
        return geisternetzRepository.findByBergendePersonIsNotNull();
    }

    public Geisternetz findById(Long id) {
        return geisternetzRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Geisternetz nicht gefunden: " + id));
    }

    @Transactional
    public Geisternetz bergungAnmelden(Long netzId, String name, String telefonnummer) {
        Geisternetz netz = findById(netzId);

        if (netz.getBergendePerson() != null) {
            throw new IllegalStateException("Dieses Netz ist bereits einer bergenden Person zugeordnet.");
        }

        BergendePerson person = new BergendePerson(name, telefonnummer);
        bergendePersonRepository.save(person);

        netz.setBergendePerson(person);
        netz.setStatus(GeisternetzStatus.BERGUNG_BEVORSTEHEND);
        return geisternetzRepository.save(netz);
    }

    @Transactional
    public Geisternetz alsGeborgenMarkieren(Long netzId) {
        Geisternetz netz = findById(netzId);
        netz.setStatus(GeisternetzStatus.GEBORGEN);
        return geisternetzRepository.save(netz);
    }
}
