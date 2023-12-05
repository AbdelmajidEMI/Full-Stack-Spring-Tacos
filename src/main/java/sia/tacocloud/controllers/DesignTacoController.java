package sia.tacocloud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.*;
import sia.tacocloud.DAO.ingredientRepo.IngredientRepository;
import sia.tacocloud.DAO.ingredientRepo.JPAIngredientRepository;
import sia.tacocloud.DAO.tacoRepo.JPATacoRepository;
import sia.tacocloud.DAO.tacoRepo.TacoRepository;
import sia.tacocloud.modules.Ingredient;
import sia.tacocloud.modules.Ingredient.Type;
import sia.tacocloud.modules.Taco;
import sia.tacocloud.modules.Order;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    List<Ingredient> ingredients = new ArrayList<>();
    private final JPAIngredientRepository ingredientRepo;

    private Type[] types;

    private JPATacoRepository designRepo;


    @Autowired
    public DesignTacoController(
            JPAIngredientRepository ingredientRepo,
            JPATacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @PostConstruct
    void onLoad() {
        this.types = Ingredient.Type.values();
        Iterable<Ingredient> foo = this.ingredientRepo.findAll();
        foo.forEach(i -> ingredients.add(i));
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        // Add an empty Taco object to the model
        model.addAttribute("design", new Taco());

        return "design";
    }




    @PostMapping
    public String processDesign(Model model,
                                @ModelAttribute("order") Order order,
                                @Valid @ModelAttribute("design") Taco design,
                                Errors errors) {
        if (errors.hasErrors()) {
            for (Type type : types) {
                model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
            }
            return "design";
        }

        Taco saved = designRepo.save(design);
        ((Order) model.getAttribute("order")).addDesign(saved);

        return "redirect:/orders/current";
    }



    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
