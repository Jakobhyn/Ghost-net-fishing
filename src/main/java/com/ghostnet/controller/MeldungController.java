package com.ghostnet.controller;

import com.ghostnet.service.MeldungService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/meldung")
public class MeldungController {

    private final MeldungService meldungService;

    public MeldungController(MeldungService meldungService) {
        this.meldungService = meldungService;
    }

    @GetMapping("/erfassen")
    public String erfassenFormular() {
        return "meldung/erfassen";
    }

    @PostMapping("/erfassen")
    public String erfassenSpeichern(@RequestParam Double latitude,
                                     @RequestParam Double longitude,
                                     @RequestParam String groesse,
                                     @RequestParam(defaultValue = "false") boolean anonym,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String telefonnummer,
                                     RedirectAttributes redirectAttributes) {
        meldungService.netzErfassen(latitude, longitude, groesse, anonym, name, telefonnummer);
        redirectAttributes.addFlashAttribute("erfolg", "Geisternetz erfolgreich gemeldet!");
        return "redirect:/geisternetze";
    }
}
