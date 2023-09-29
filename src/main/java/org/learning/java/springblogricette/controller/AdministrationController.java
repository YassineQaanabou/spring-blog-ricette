package org.learning.java.springblogricette.controller;

import org.learning.java.springblogricette.model.Recipe;
import org.learning.java.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administration")
public class AdministrationController {
    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping
    public String index(Model model) {
        List<Recipe> recipeList;
        recipeList = recipeRepository.findAll();
        model.addAttribute("recipes", recipeList);
        return "administration/list";
    }
}
