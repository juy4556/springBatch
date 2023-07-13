package springbatch.domain.security.service;

import jakarta.transaction.NotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import springbatch.domain.security.entity.RefreshToken;
import springbatch.domain.security.jwt.JwtUtils;
import springbatch.domain.security.repository.RefreshTokenRepository;
import springbatch.domain.user.dto.response.TokenRefreshResponse;
import springbatch.domain.user.entity.User;
import springbatch.domain.user.repository.UserRepository;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${security.jwt.token.refreshExpirationMs}")
    private Long refreshTokenDurationMs;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public RefreshToken createRefreshToken(String token) {
        RefreshToken refreshToken =
                new RefreshToken(token, UUID.randomUUID().toString(), Instant.now().plusMillis(refreshTokenDurationMs));
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public TokenRefreshResponse refreshToken(Long userId, String accessToken) throws NotSupportedException {
        Optional<RefreshToken> refreshTokenExist = refreshTokenRepository.findByAccessToken(accessToken);
        if (refreshTokenExist.isEmpty()) {
            throw new NoSuchElementException("refreshToken이 DB에 존재하지 않습니다.");
        }
        RefreshToken refreshToken = verifyExpiration(refreshTokenExist.get());
        Optional<User> userExist = userRepository.findById(userId);
        if (userExist.isEmpty()) {
            throw new NoSuchElementException("해당 유저가 존재하지 않습니다.");
        }
        String newToken = jwtUtils.generateJwtToken(userExist.get());
        return new TokenRefreshResponse(newToken, refreshToken.getRefreshToken(), refreshToken.getCreatedAt());
    }

    private RefreshToken verifyExpiration(RefreshToken token) throws NotSupportedException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new NotSupportedException("리프레시토큰이 만료되었습니다.");
        }
        return token;
    }
}
