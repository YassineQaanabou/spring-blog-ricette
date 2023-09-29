package org.learning.java.springblogricette.repository;

import org.learning.java.springblogricette.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByTitleOrIngredientsOrPreparation(String TitleSearch, String ingredientSearch, String preparationSearch);

}
