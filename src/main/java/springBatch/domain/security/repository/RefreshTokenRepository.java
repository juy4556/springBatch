package springbatch.domain.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbatch.domain.security.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByAccessToken(String accessToken);

}
