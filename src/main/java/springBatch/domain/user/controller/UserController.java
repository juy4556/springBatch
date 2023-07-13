package springbatch.domain.user.controller;

import jakarta.transaction.NotSupportedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbatch.domain.security.jwt.JwtAuth;
import springbatch.domain.security.service.RefreshTokenService;
import springbatch.domain.user.dto.request.LoginRequest;
import springbatch.domain.user.dto.request.SignupRequest;
import springbatch.domain.user.dto.request.TokenRefreshRequest;
import springbatch.domain.user.dto.response.LoginResponse;
import springbatch.domain.user.dto.response.SignupResponse;
import springbatch.domain.user.dto.response.TokenRefreshResponse;
import springbatch.domain.user.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> register(@RequestBody @Valid final SignupRequest request) {
        SignupResponse signupResponse = userService.signUp(request);
        return ResponseEntity.ok().body(signupResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid final LoginRequest request) {
        LoginResponse loginResponse = userService.login(request);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<TokenRefreshResponse> login(@JwtAuth Long userId, @RequestBody TokenRefreshRequest request)
            throws NotSupportedException {
        String accessToken = request.getAccessToken();
        TokenRefreshResponse loginResponse = refreshTokenService.refreshToken(userId, accessToken);
        return ResponseEntity.ok().body(loginResponse);
    }
}
