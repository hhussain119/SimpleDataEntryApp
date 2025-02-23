package com.example.dataentryapp.controller;

import com.example.dataentryapp.model.UserEntry;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserEntryController {

    private final List<UserEntry> userEntries = new ArrayList<>();

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userEntry", new UserEntry());
        return "index";
    }

    @PostMapping("/submit")
    public String submitForm(@Valid @ModelAttribute("userEntry") UserEntry userEntry,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        userEntries.add(userEntry);
        model.addAttribute("userEntry", userEntry);
        model.addAttribute("userEntries", userEntries);

        return "confirmation";
    }
}