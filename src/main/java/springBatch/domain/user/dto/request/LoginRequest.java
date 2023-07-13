package springbatch.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginRequest {
    @NotBlank(message = "이름을 입력하세요.")
    private String name;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(message = "숫자 4자와 영문자 1자입니다.", regexp = "^\\d{4}[a-zA-Z]$")
    private String password;
}
