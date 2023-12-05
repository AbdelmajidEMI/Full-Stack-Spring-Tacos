package sia.tacocloud.modules;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class Order {
    private Long id;
    private Date placedAt;
    private Set<Taco> tacos = new HashSet<>();



    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="Street is required")
    private String street;
    @NotBlank(message="City is required")
    private String city;
    @NotBlank(message="State is required")
    private String state;
    @NotBlank(message="Zip code is required")
    private String zip;
    @CreditCardNumber(message="Not a valid credit card number")
    private String number;
    @Pattern(regexp="^(0[1-9]|1[0-2])/([0-9]{2})$", message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction = 0, message = "Invalid CVV")
    private String ccCvv;


    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

}