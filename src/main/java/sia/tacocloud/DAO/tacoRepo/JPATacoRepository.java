package sia.tacocloud.DAO.tacoRepo;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.modules.Taco;

public interface JPATacoRepository extends CrudRepository<Taco, Long> {
}
