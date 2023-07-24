package UMCFatMan.fatman.domain.fatman.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddFatmanRequestDto {

    private Long userId ; // 임시 -> 추후 token으로 확인해 변경 예정

}
