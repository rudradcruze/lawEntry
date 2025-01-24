package com.mohtashim.sami.lawEntry.controller;

import com.mohtashim.sami.lawEntry.model.LawEntry;
import com.mohtashim.sami.lawEntry.service.LawEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PrimaryController {

    @Autowired
    private LawEntryService lawEntryService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        if (principal == null)
            return "redirect:/login";

        model.addAttribute("title", "Law Entry");
        model.addAttribute("lawEntry", new LawEntry());
        model.addAttribute("lawEntries", lawEntryService.getAll());

        return "index";
    }
}
