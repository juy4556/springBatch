package springbatch.domain.user.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignupResponse {
    private final Long id;
    private final String name;
}
