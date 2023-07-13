package springbatch.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbatch.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
}
