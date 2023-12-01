package sia.tacocloud.modules;
import lombok.Data;
@Data
public class Order {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String number;
    private String ccExpiration;
    private String ccCvv;
}