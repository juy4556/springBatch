package springbatch.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbatch.domain.user.dto.response.SignupResponse;
import springbatch.domain.user.dto.request.SignupRequest;
import springbatch.domain.user.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> register(@RequestBody @Valid final SignupRequest request) {
        SignupResponse signupResponse = userService.signUp(request);
        return ResponseEntity.ok().body(signupResponse);
    }
}
