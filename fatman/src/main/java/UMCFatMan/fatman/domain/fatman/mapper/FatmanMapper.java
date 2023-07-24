package UMCFatMan.fatman.domain.fatman.mapper;

import UMCFatMan.fatman.domain.fatman.dto.AddFatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.FatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.fatman.entity.UserFatman;
import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FatmanMapper {
    public UserFatman toEntity(Users user, Fatman fatman) {
        return new UserFatman(user, fatman);
    }

    public FatmanResponseDto toResponseDto(List<Long> fatmanId) {
        return new FatmanResponseDto(fatmanId);
    }

}
