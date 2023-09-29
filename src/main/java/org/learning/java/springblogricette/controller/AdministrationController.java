package org.learning.java.springblogricette.controller;

import jakarta.validation.Valid;
import org.learning.java.springblogricette.model.Category;
import org.learning.java.springblogricette.model.Recipe;
import org.learning.java.springblogricette.repository.CategoryRepository;
import org.learning.java.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/administration")
public class AdministrationController {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public String index(Model model) {
        List<Recipe> recipeList;
        recipeList = recipeRepository.findAll();
        model.addAttribute("recipes", recipeList);
        return "administration/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Recipe> recipeList = recipeRepository.findAll();
        List<Category> categoryList = categoryRepository.findAll();

        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categoryList);


        return "administration/form";
    }


    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Recipe formRecipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "administration/form";
        }
        recipeRepository.save(formRecipe);
        return "redirect:/administration";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("recipe", result.get());
            model.addAttribute("categories", categoryList);

            return "administration/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "recipe with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Recipe formRecipe,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/administration/edit"; //
        }
        recipeRepository.save(formRecipe);
        return "redirect:/administration/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        recipeRepository.deleteById(id);
        return "redirect:/administration";
    }

}
