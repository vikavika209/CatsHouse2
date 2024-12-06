package org.example.controllers;
import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.example.model.Cat;
import org.example.model.CatHouse;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;




@Controller
@Validated
public class CatsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatsController.class);

    @GetMapping("/")
    public String catsList(Model model) {
        LOGGER.info("Fetching list of cats");
        model.addAttribute("cats", CatHouse.getCatList());
        return "cats-list";
    }

    @GetMapping("/create-form")
    public String addCat(Model model) {
        LOGGER.debug("Opening create form for new cat");
        model.addAttribute("cat", new Cat());
        return "create-update-form";
    }

    @PostMapping("/create")
    public String createCat(@Valid @ModelAttribute("cat") Cat cat, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.warn("Validation errors while creating cat: {}", bindingResult.getFieldErrors());
            return "create-update-form";
        }

        cat.setId(UUID.randomUUID().toString());
        CatHouse.getCatList().add(cat);
        LOGGER.info("Created new cat with ID: {}, Name: {}", cat.getId(), cat.getName());
        return "redirect:/";
    }

    @GetMapping("/edit-form/{id}")
    public String editCat(@PathVariable("id") String id, Model model) {
        LOGGER.debug("Editing cat with ID: {}", id);
        try {
            Cat catToEdit = CatHouse.getCatList().stream()
                    .filter(cat -> cat.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cat not found with ID: " + id));
            model.addAttribute("cat", catToEdit);
            return "edit-form";
        } catch (RuntimeException e) {
            LOGGER.error("Error occurred while editing cat with ID: {}", id, e);
            throw e;
        }
    }

    @PostMapping("/update")
    String updateCat(@Valid Cat cat) {
        LOGGER.debug("Updating cat with ID: {}", cat.getId());
        Cat catToUpdate = CatHouse.getCatList().stream()
                .filter(cat1 -> cat1.getId().equals(cat.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cat not found with ID: " + cat.getId()));
        catToUpdate.setName(cat.getName());
        catToUpdate.setBreed(cat.getBreed());
        catToUpdate.setAge(cat.getAge());
        LOGGER.info("Updated cat with ID: {}, New Name: {}", catToUpdate.getId(), catToUpdate.getName());
        return "redirect:/";
    }
}

