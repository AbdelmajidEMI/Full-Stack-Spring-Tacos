package sia.tacocloud;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.modules.Ingredient;
import sia.tacocloud.DAO.ingredientRepo.IngredientRepository;

@Component
public class StringToIngredientConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findOne(id);
    }
}
