package com.mohtashim.sami.lawEntry.controller;

import com.mohtashim.sami.lawEntry.model.LawEntry;
import com.mohtashim.sami.lawEntry.service.LawEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LawEntryController {
    @Autowired
    private LawEntryService lawEntryService;

    @PostMapping("/lawEntry/new")
    public String lawEntrySave(@Valid @ModelAttribute("lawEntry") LawEntry lawEntry,
                               BindingResult result,
                               RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("error", "Invalid input format");
            return "redirect:/";
        }

        lawEntryService.save(lawEntry);
        attributes.addFlashAttribute("success", "Law Case Successfully Added");
        return "redirect:/";
    }

    @GetMapping("/lawEntry/edit/{id}")
    public String lawEntryEdit(@PathVariable String id,
                              Model model, Principal principal) {

        if (principal == null) return "redirect:/login";

        LawEntry lawEntry = lawEntryService.get(Long.parseLong(id));
        model.addAttribute("lawEntryEdit", lawEntry);
        model.addAttribute("lawEntry", new LawEntry());

        return "edit_lawEntry";
    }

    @PostMapping("/lawEntry/edit/save")
    public String lawEntryEditSave(@ModelAttribute("lawEntryEdit") LawEntry lawEntryEdit,
                                  Principal principal, RedirectAttributes attributes) {
        if (principal == null) return "redirect:/login";

        LawEntry lawEntry = lawEntryService.get(lawEntryEdit.getId());

        lawEntry.setCaseName(lawEntryEdit.getCaseName());
        lawEntry.setClientName(lawEntryEdit.getClientName());
        lawEntry.setDescription(lawEntryEdit.getDescription());
        lawEntry.setDate(lawEntryEdit.getDate());

        lawEntryService.save(lawEntry);
        attributes.addFlashAttribute("success", "Law Case edit successful");

        return "redirect:/";
    }


    @GetMapping("/lawEntry/delete/{id}")
    public String lawEntryEdit(@PathVariable String id,
                              Model model, Principal principal,
                              RedirectAttributes attributes) {

        if (principal == null) return "redirect:/login";
        lawEntryService.delete(Long.parseLong(id));
        attributes.addFlashAttribute("success", "Law Case delete successful");
        return "redirect:/";
    }
}
