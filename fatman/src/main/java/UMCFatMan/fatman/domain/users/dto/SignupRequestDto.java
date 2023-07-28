package UMCFatMan.fatman.domain.users.dto;

import UMCFatMan.fatman.domain.users.entity.Users;
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
    private String nickname ;
    private String address ;
    private String birth;

    public Users getUser(SignupRequestDto dto, PasswordEncoder encoder) {
        return Users.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(encoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .address(dto.getAddress())
                .birth(dto.getBirth())
                .build();

    };

}
