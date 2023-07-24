package UMCFatMan.fatman.domain.users;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    private String email;
    private String name;
    private String password;

    Users getUser(SignupRequestDto dto, PasswordEncoder encoder) {
        return Users.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(encoder.encode(dto.getPassword()))
                .build();

    };

}
