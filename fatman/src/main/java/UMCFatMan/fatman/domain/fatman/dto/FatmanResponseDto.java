package UMCFatMan.fatman.domain.fatman.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FatmanResponseDto {

    private Long FatmanId;

    private String Name ;

    private String FatmanImageUrl ;

    private int FatmanCost;
}
