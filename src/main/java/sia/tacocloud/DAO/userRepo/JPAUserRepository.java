package sia.tacocloud.DAO.userRepo;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.modules.User;

public interface JPAUserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
