package springbatch.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbatch.domain.user.dto.request.SignupRequest;
import springbatch.domain.user.dto.response.SignupResponse;
import springbatch.domain.user.entity.User;
import springbatch.domain.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public SignupResponse signUp(SignupRequest request) {
        String password = request.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(request.getName(), request.getBirth(), encodedPassword);
        userRepository.save(user);
        return new SignupResponse(user.getId(), user.getName());
    }
}
