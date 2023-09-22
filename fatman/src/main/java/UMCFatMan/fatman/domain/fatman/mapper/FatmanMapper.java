package UMCFatMan.fatman.domain.fatman.mapper;

import UMCFatMan.fatman.domain.fatman.dto.FatmanRequestDto;
import UMCFatMan.fatman.domain.fatman.dto.FatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.dto.UserFatmanResponseDto;
import UMCFatMan.fatman.domain.fatman.entity.Fatman;
import UMCFatMan.fatman.domain.fatman.entity.UserFatman;
import UMCFatMan.fatman.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FatmanMapper {

    // 유저 펫맨
    public UserFatman toEntity(Users user, Fatman fatman) {
        return new UserFatman(user, fatman);
    }

    public UserFatmanResponseDto toResponseDto(List<Long> fatmanId) {
        return new UserFatmanResponseDto(fatmanId);
    }


    // 팻맨
//    public Fatman requestEntity(FatmanRequestDto fatmanRequestDto) {
//        return Fatman.builder()
//                .name(fatmanRequestDto.getName())
//                .build();
//    }


    public static FatmanResponseDto fromEntity(Fatman fatman) {
        return FatmanResponseDto.builder()
                .FatmanId(fatman.getId())
                .Name(fatman.getName())
                .FatmanImageUrl(fatman.getFatmanImageUrl())
                .FatmanCost(fatman.getCost())
                .build();
    }

}