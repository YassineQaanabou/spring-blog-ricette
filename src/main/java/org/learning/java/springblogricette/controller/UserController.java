package org.learning.java.springblogricette.controller;

import org.learning.java.springblogricette.model.Recipe;
import org.learning.java.springblogricette.repository.CategoryRepository;
import org.learning.java.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(@RequestParam(name = "keyword") Optional<String> searchKeyword,
                        Model model) {
        List<Recipe> recipeList;

        String keyword = "";
        if (searchKeyword.isPresent()) {
            keyword = searchKeyword.get();
            recipeList = recipeRepository.findByTitleOrIngredientsOrPreparation(keyword, keyword, keyword);
        } else {
            recipeList = recipeRepository.findAll();
        }

        model.addAttribute("recipes", recipeList);
        model.addAttribute("keyword", keyword);
        return "user/home";
    }

    @GetMapping("/show/{recipeID}")
    public String show(@PathVariable("recipeID") Integer id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipeFromDB = recipeOptional.get();
            model.addAttribute("recipe", recipeFromDB);
            return "user/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}
