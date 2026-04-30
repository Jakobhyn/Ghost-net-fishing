package com.ghostnet.service;

import com.ghostnet.entity.Geisternetz;
import com.ghostnet.entity.MeldendePerson;
import com.ghostnet.entity.Meldung;
import com.ghostnet.repository.GeisternetzRepository;
import com.ghostnet.repository.MeldungRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeldungService {

    private final MeldungRepository meldungRepository;
    private final GeisternetzRepository geisternetzRepository;

    public MeldungService(MeldungRepository meldungRepository,
                          GeisternetzRepository geisternetzRepository) {
        this.meldungRepository = meldungRepository;
        this.geisternetzRepository = geisternetzRepository;
    }

    @Transactional
    public Meldung netzErfassen(Double latitude, Double longitude, String groesse,
                                 boolean anonym, String name, String telefonnummer) {
        Geisternetz netz = new Geisternetz(latitude, longitude, groesse);
        geisternetzRepository.save(netz);

        MeldendePerson person = null;
        if (!anonym) {
            person = new MeldendePerson(name, telefonnummer, false);
        } else {
            person = new MeldendePerson(null, null, true);
        }

        Meldung meldung = new Meldung(netz, person);
        return meldungRepository.save(meldung);
    }
}
