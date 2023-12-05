package sia.tacocloud.modules;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Set;

@Data
public class Taco {
    private Long id;
    private Date createdAt;

    @NotNull(message="You must choose at least 1 ingredient")
    @Size(min=5, message = "Name must be at least 5 caracters long")
    private String name;
    @NotNull(message="You must choose at least 1 ingredient")
    @Size(min=1, message="You must choose at least 1 ingredient")
    private Set<Ingredient> ingredients;
}