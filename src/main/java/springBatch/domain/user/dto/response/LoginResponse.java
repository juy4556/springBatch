package springbatch.domain.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class LoginResponse {

    private String userName;
    private String accessToken;
    private Long refreshTokenId;
    private LocalDateTime createdAt;

    public LoginResponse(String userName, String accessToken, Long refreshTokenId, LocalDateTime createdAt) {
        this.userName = userName;
        this.accessToken = accessToken;
        this.refreshTokenId = refreshTokenId;
        this.createdAt = createdAt;
    }
}
