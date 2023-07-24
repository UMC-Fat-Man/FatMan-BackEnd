package UMCFatMan.fatman.domain.users.mapper;

import UMCFatMan.fatman.domain.users.dto.SignUpRequestDto;
import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public Users toEntity(SignUpRequestDto signUpRequestDto) {
        return Users.builder()
                .email(signUpRequestDto.getEmail())
                .name(signUpRequestDto.getName())
                .nickname(signUpRequestDto.getNickname())
                .address(signUpRequestDto.getAddress())
                .birth(signUpRequestDto.getBirth())
                .activated(true)
                .build();
    }

}
