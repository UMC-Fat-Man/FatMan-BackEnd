package UMCFatMan.fatman.domain.monster.dto;


import UMCFatMan.fatman.domain.monster.domain.Monster;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MonsterRequestDto {
    private String name;
    private String imageUrl;

    public Monster toMonster(String imageUrl) {
        return Monster.builder()
                .imageUrl(imageUrl)
                .name(name)
                .build();
    }
}
