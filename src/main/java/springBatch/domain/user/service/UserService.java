package springbatch.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbatch.domain.security.entity.RefreshToken;
import springbatch.domain.security.jwt.JwtUtils;
import springbatch.domain.security.service.RefreshTokenService;
import springbatch.domain.user.dto.request.LoginRequest;
import springbatch.domain.user.dto.response.LoginResponse;
import springbatch.domain.user.dto.request.SignupRequest;
import springbatch.domain.user.dto.response.SignupResponse;
import springbatch.domain.user.entity.User;
import springbatch.domain.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupResponse signUp(SignupRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getName(), request.getBirth(), encodedPassword);
        userRepository.save(user);
        return new SignupResponse(user.getId(), user.getName());
    }
}
