package UMCFatMan.fatman.domain.users.mapper;

import UMCFatMan.fatman.domain.users.dto.SocialDetailRequestDto;
import UMCFatMan.fatman.domain.users.dto.UserDetailResponseDto;
import UMCFatMan.fatman.domain.users.dto.UserEmailNameDto;
import UMCFatMan.fatman.domain.users.entity.AuthProvider;
import UMCFatMan.fatman.domain.users.entity.Role;
import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public static Users toUserEntity(UserEmailNameDto userEmailNameDto) {
        Users user = new Users();
        user.setEmail(userEmailNameDto.getEmail());
        user.setName(userEmailNameDto.getName());
        user.setRole(Role.ROLE_USER);
        user.setActivated(true);
        user.setAuthProvider(AuthProvider.GOOGLE);
        return user;
    }

    public static void updateUserEntity(Users user, SocialDetailRequestDto socialDetailRequestDto) {
        user.setNickname(socialDetailRequestDto.getNickname());
        user.setAddress(socialDetailRequestDto.getAddress());
        user.setBirth(socialDetailRequestDto.getBirth());
    }


    public static Users toUserEntity(UserDetailResponseDto responseDto) {
        Users user = new Users();
        user.setEmail(responseDto.getEmail());
        user.setName(responseDto.getName());
        user.setNickname(responseDto.getNickname());
        user.setAddress(responseDto.getAddress());
        user.setBirth(responseDto.getBirth());
        return user;
    }

    public static UserDetailResponseDto toUserDetailResponseDto(Users user) {
        UserDetailResponseDto responseDto = new UserDetailResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setName(user.getName());
        responseDto.setNickname(user.getNickname());
        responseDto.setAddress(user.getAddress());
        responseDto.setBirth(user.getBirth());
        return responseDto;
    }

}
