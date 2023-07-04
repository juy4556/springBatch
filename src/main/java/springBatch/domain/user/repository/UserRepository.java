package springbatch.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbatch.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
