package sia.tacocloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sia.tacocloud.DAO.userRepo.JPAUserRepository;
import sia.tacocloud.modules.User;



@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private final JPAUserRepository userRepo;
    @Autowired
    public UserRepositoryUserDetailsService(JPAUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        System.out.println();
        System.out.println();
        System.out.println(user);
        System.out.println();
        System.out.println();
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }
}
