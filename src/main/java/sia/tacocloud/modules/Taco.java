package sia.tacocloud.modules;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class Taco {
    private String name; // Name of the taco
    private List<Ingredient> ingredients; // List of ingredients in the taco
}
