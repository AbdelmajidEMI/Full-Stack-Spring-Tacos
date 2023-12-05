package sia.tacocloud.DAO.ingredientRepo;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.modules.Ingredient;

public interface JPAIngredientRepository extends CrudRepository<Ingredient, String> {

}
