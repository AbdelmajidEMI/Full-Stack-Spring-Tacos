package sia.tacocloud.DAO.ingredientRepo;


import sia.tacocloud.modules.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);

}