package springbatch.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public SignupResponse signUp(SignupRequest request) {
        log.info("실행됨?");
        User user = new User(request.getName(), request.getBirth(), request.getPassword());
        userRepository.save(user);
        return new SignupResponse(user.getId(), user.getName());
    }
}
