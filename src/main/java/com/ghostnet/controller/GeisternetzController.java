package com.ghostnet.controller;

import com.ghostnet.entity.Geisternetz;
import com.ghostnet.service.GeisternetzService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/geisternetze")
public class GeisternetzController {

    private final GeisternetzService geisternetzService;

    public GeisternetzController(GeisternetzService geisternetzService) {
        this.geisternetzService = geisternetzService;
    }

    @GetMapping
    public String alleNetze(Model model) {
        List<Geisternetz> netze = geisternetzService.alleNetze();
        model.addAttribute("netze", netze);
        return "geisternetz/liste";
    }

    @GetMapping("/zu-bergen")
    public String zuBergendeNetze(Model model) {
        model.addAttribute("netze", geisternetzService.zuBergendeNetze());
        return "geisternetz/zu-bergen";
    }

    @GetMapping("/bergung/{id}")
    public String bergungFormular(@PathVariable Long id, Model model) {
        model.addAttribute("netz", geisternetzService.findById(id));
        return "geisternetz/bergung-formular";
    }

    @PostMapping("/bergung/{id}")
    public String bergungSpeichern(@PathVariable Long id,
                                    @RequestParam String name,
                                    @RequestParam String telefonnummer,
                                    RedirectAttributes redirectAttributes) {
        try {
            geisternetzService.bergungAnmelden(id, name, telefonnummer);
            redirectAttributes.addFlashAttribute("erfolg", "Bergung erfolgreich eingetragen!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("fehler", e.getMessage());
        }
        return "redirect:/geisternetze/zu-bergen";
    }

    @PostMapping("/geborgen/{id}")
    public String alsGeborgenMarkieren(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        geisternetzService.alsGeborgenMarkieren(id);
        redirectAttributes.addFlashAttribute("erfolg", "Netz als geborgen markiert!");
        return "redirect:/geisternetze";
    }

    @GetMapping("/uebersicht")
    public String bergungsUebersicht(Model model) {
        model.addAttribute("netze", geisternetzService.netzeWithBergendePerson());
        return "geisternetz/uebersicht";
    }
}
